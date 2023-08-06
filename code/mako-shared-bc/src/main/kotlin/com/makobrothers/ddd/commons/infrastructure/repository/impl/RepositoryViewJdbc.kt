package com.makobrothers.ddd.commons.infrastructure.repository.impl

import com.makobrothers.ddd.commons.dto.Page
import com.makobrothers.ddd.commons.dto.criteria.BaseCriteria
import com.makobrothers.ddd.commons.dto.model.SqlParametrizable
import com.makobrothers.ddd.commons.infrastructure.jdbc.Query
import com.makobrothers.ddd.commons.infrastructure.repository.RepositoryView
import org.slf4j.*
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.*
import org.springframework.jdbc.core.support.JdbcDaoSupport
import java.io.Serializable
import java.lang.reflect.ParameterizedType
import java.util.*
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

/**
 * Plantilla genérica para los Repository. Para no repetir los Repository's utilizamos
 * generics de java. Ya que todas los Repository's a priori hacen las mismas
 * operaciones.

 * @param T - Clase de los objetos a consultar en BD.
 * 
 * @param PK - Tipo básico de la surrogate key
 * 
 * @param R - RowMapper mapeador de filas sql a objeto de tipo T
 * 
 * @param C - Criteria para montar la cláusula where, order by etc, de las queries.
 * 
 * @param Q - Query para montar el sql de las queries. Es un bean con valores que definen
 *           como crear la query.
 * 
 * 
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
open class RepositoryViewJdbc<T: SqlParametrizable<String>,
    in PK: Serializable,
    R: RowMapper<T>,
    in C: BaseCriteria,
    Q: Query<C>> : JdbcDaoSupport(), RepositoryView<T, PK, C> {
    private val log: Logger = LoggerFactory.getLogger(javaClass)
    private var rowMapperClass: Class<R>? = null
    protected var queryClass: Class<Q>? = null
    var namedParameterJdbcOperations: NamedParameterJdbcOperations? = null

    init {
        val genericClass = getGenericClass()
        val parameterizedType = genericClass.genericSuperclass as ParameterizedType
        rowMapperClass = parameterizedType.actualTypeArguments[ROWMAPPER_CLASS_INDEX] as Class<R>
        queryClass = parameterizedType.actualTypeArguments[QUERY_CLASS_INDEX] as Class<Q>
    }

    private fun getGenericClass(): Class<*> {
        var genericClass: Class<*> = javaClass
        while (RepositoryViewJdbc::class.java.simpleName != genericClass.superclass.simpleName) {
            if (genericClass.genericSuperclass is ParameterizedType)
                break
            genericClass = genericClass.superclass
        }
        return genericClass
    }

    /**
     * Devuelve todos los objetos de tipo T que cumplan los criterios y el filtro si los tienen.

     * @param criteria Dto con criterios de busqueda
     * 
     * @return List con todos los objetos de tipo T que cumplen los criterios
     */
    override fun findByCriteria(criteria: C): List<T> {
        return findByCriteria(queryClass!!.getDeclaredConstructor().newInstance().toSQLFetchRows(criteria), criteria)
    }

    /**
     * Devuelve todos los objetos de tipo T que cumplan los criterios y el filtro si los tienen.

     * @param namedQuery IQuery customizada que se desea ejecutar.
     * 
     * @param criteria Dto con criterios de busqueda
     * 
     * @return List con todos los objetos de tipo T que cumplen los criterios
     */
    private fun findByCriteria(namedQuery: String, criteria: C): List<T> {
        return namedParameterJdbcOperations!!.query(namedQuery, criteria.mapSQLParameterSource, rowMapperClass!!.getDeclaredConstructor().newInstance())
    }

    /**
     * Devuelve todas los objetos de tipo T que cumplan los criterios y el filtro si los tienen
     * en un objeto Page.

     * @param criteria - Dto con criterios de búsqueda
     * 
     * @return Page con todas las entidades tipo T y contadores que cumplen el criterio.
     */
    override fun findPageByCriteria(criteria: C): Page<T> {
        var criteriaAux = setMinimunPageSizeEqualsOrGreaterAsDefault(criteria)
        criteriaAux = setActualPageInsideLimits(criteriaAux)
        val query = queryClass!!.getDeclaredConstructor().newInstance()
        return findPageByCriteria(query.toSQLFetchRows(criteriaAux), query.toSQLRowCount(criteria), criteria)
    }

    private fun setMinimunPageSizeEqualsOrGreaterAsDefault(criteria: C): C {
        if (criteria.pageSize < MINIMUN_PAGE_SIZE)
            criteria.pageSize = DEFAULT_PAGE_SIZE
        return criteria
    }

    private fun setActualPageInsideLimits(criteria: C): C {
        if (criteria.actualPage < MINIMUN_PAGE_NUMBER)
            criteria.actualPage = MINIMUN_PAGE_NUMBER
        else if (criteria.totalPages > 0 && criteria.actualPage > criteria.totalPages)
            criteria.actualPage = criteria.totalPages
        return criteria
    }

    /**
     * Devuelve todas los objetos de tipo T que cumplan los criterios y el filtro si los tienen
     * en un objeto Page.

     * @param namedQuery IQuery customizada que se desea ejecutar.
     * 
     * @param rowCountQuery IQuery para contar el número total de filas.
     * 
     * @param criteria - Dto con criterios de búsqueda
     * 
     * @return Page con todas las entidades tipo T y contadores que cumplen el criterio.
     */
    private fun findPageByCriteria(namedQuery: String, rowCountQuery: String, criteria: C): Page<T> {
        val sqlFetchRows = addLimitAndOffset(namedQuery, criteria)
        val rowMapper = rowMapperClass!!.getDeclaredConstructor().newInstance()
        val res = namedParameterJdbcOperations!!.query(sqlFetchRows, criteria.mapSQLParameterSource, rowMapper)
        val totalRows = namedParameterJdbcOperations!!.queryForObject(rowCountQuery, criteria.mapSQLParameterSource, Int::class.java)
        val resultPage = Page<T>()
        resultPage.content = res
        return calculateCountersPage(resultPage, criteria, totalRows)
    }

    private fun addLimitAndOffset(query: String, criteria: C): String {
        val queryLimited = StringBuilder(query)
        queryLimited.append(SQL_WORD_LIMIT)
        queryLimited.append(criteria.pageSize)
        queryLimited.append(SQL_WORD_OFFSET)
        var offset = 0
        if (criteria.actualPage > MINIMUN_PAGE_NUMBER)
            offset = (criteria.actualPage - 1) * criteria.pageSize
        return queryLimited.append(offset).toString()
    }

    private fun calculateCountersPage(page: Page<T>, criteria: C?, totalRows: Int): Page<T> {
        page.totalElements = totalRows.toLong()
        page.totalPages = calculateTotalPages(criteria, totalRows)
        page.size = calculatePageSize(page)
        page.number = criteria!!.actualPage
        return page
    }

    private fun calculateTotalPages(criteria: C?, totalRows: Int): Int {
        var totalPages = 0
        if (criteria?.pageSize != 0) {
            totalPages = if (totalRows <= criteria!!.pageSize) {
                1
            } else {
                Math.ceil((totalRows.toDouble() / criteria.pageSize.toDouble())).toInt()
            }
        }
        return totalPages
    }

    private fun calculatePageSize(page: Page<T>): Int {
        var pageSize = 0
        if (page.content.isNotEmpty()) {
            pageSize = page.content.size
        }
        return pageSize
    }

    /**
     * Obtiene un objeto T de la base de datos

     * @param id - El identificador de la entidad T
     * 
     * @return La entidad T
     */
    override fun findById(id: PK): T? {
        val query = queryClass!!.getDeclaredConstructor().newInstance()
        val sql = query.toSQLFindById()
        val parameters = getParameterId(id, query)
        val rowMapper = rowMapperClass!!.getDeclaredConstructor().newInstance()
        return executeQuery(sql, parameters, rowMapper)
    }

    private fun getParameterId(id: PK, query: Q): HashMap<String, Any> {
        val keyNamePrimary = query.namePrimaryKey
        val parameters = HashMap<String, Any>()
        parameters[keyNamePrimary] = id
        return parameters
    }

    private fun executeQuery(sql: String, parameters: HashMap<String, Any>, rowMapper: R): T? {
        var dto: T? = null
        try {
            dto = namedParameterJdbcOperations!!.queryForObject(sql, parameters, rowMapper)
        } catch (e: EmptyResultDataAccessException) {
        } catch (e: Exception) {
            throw e
        }
        return dto
    }

    /**
     * Muestra en las trazas de log los parámetros de la query.

     * @param mapSqlParameterSource Parámetros de la consulta.
     */
    private fun debugSqlParameters(mapSqlParameterSource: MapSqlParameterSource) {
        if (isNotEmptyMapSqlParameterSource(mapSqlParameterSource)) {
            for ((key, value) in mapSqlParameterSource.values) {
                log.debug("$key: $value")
            }
        }
    }

    private fun isNotEmptyMapSqlParameterSource(mapSqlParameterSource: MapSqlParameterSource?): Boolean {
        return (mapSqlParameterSource?.values?.entries != null)
    }

    companion object {
        private const val ROWMAPPER_CLASS_INDEX = 2
        private const val QUERY_CLASS_INDEX = 4
        private const val DEFAULT_PAGE_SIZE = 10
        private const val MINIMUN_PAGE_SIZE = 1
        private const val MINIMUN_PAGE_NUMBER = 1
        private const val SQL_WORD_LIMIT = " LIMIT "
        private const val SQL_WORD_OFFSET = " OFFSET "
    }
}