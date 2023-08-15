package com.makobrothers.shared.commons.infrastructure.repository.impl

import com.makobrothers.ddd.commons.dto.criteria.BaseCriteria
import com.makobrothers.ddd.commons.dto.model.SqlParametrizable
import com.makobrothers.ddd.commons.infrastructure.jdbc.Query
import com.makobrothers.ddd.commons.infrastructure.repository.Repository
import org.springframework.jdbc.core.RowMapper
import java.io.Serializable
import java.util.*

/**
 * Plantilla genérica para los Repository. Para no repetir los Repository's utilizamos
 * generics de java. Ya que todas los Repository's a priori hacen las mismas
 * operaciones.

 * @param T - Clase de los objetos a persistir/manipular en BD.
 * *
 * @param PK - Tipo básico de la surrogate key
 * *
 * @param R - RowMapper mapeador de filas sql a objeto de tipo T
 * *
 * @param C - Criteria para montar la cláusula where, order by etc, de las queries.
 * *
 * @param Q - IQuery para montar el sql de las queries. Es un bean con valores que definen
 * *          como crear la query.
 * *
 * *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
open class RepositoryJdbc<T: SqlParametrizable<String>,
    in PK: Serializable,
    R: RowMapper<T>,
    in C: BaseCriteria,
    Q: Query<C>> : RepositoryViewJdbc<T,PK,R,C,Q>(), Repository<T, PK, C> {

    /**
     * Inserta un objeto T

     * @param dto - Un dto de la entidad T
     * *
     * @return La entidad T.
     */
    override fun insert(dto: T): T {
        namedParameterJdbcOperations!!.update(queryClass!!.getDeclaredConstructor().newInstance().toSQLInsert(), dto.toMapSqlParameterSource())
        return dto
    }

    /**
     * Actualiza un objeto T

     * @param dto - Un dto de la entidad T
     * *
     * @return La entidad T.
     */
    override fun update(dto: T): T {
        val parameters = dto.toMapSqlParameterSource()
        val rows = namedParameterJdbcOperations!!.update(queryClass!!.getDeclaredConstructor().newInstance().toSQLUpdate(), parameters)
        if (rows != 0)
            dto.version = dto.version + 1
        return dto
    }

    /**
     * Elimina un objeto T

     * @param id - Identificador del dto de tipo T
     * *
     * @return Número de filas borradas
     */
    override fun delete(id: PK): Int {
        val parameters = HashMap<String, Any>()
        val query = queryClass!!.getDeclaredConstructor().newInstance()
        parameters[query.namePrimaryKey] = id
        return namedParameterJdbcOperations!!.update(queryClass!!.getDeclaredConstructor().newInstance().toSQLDeleteById(), parameters)
    }

    /**
     * Elimina n objetos T por Criteria.

     * @param criteria Criteria para borrar
     * *
     * @return Número de filas borradas
     */
    override fun deleteByCriteria(criteria: C): Int {
        return namedParameterJdbcOperations!!.update(queryClass!!.getDeclaredConstructor().newInstance().toSQLDelete(criteria), criteria.mapSQLParameterSource)
    }
}