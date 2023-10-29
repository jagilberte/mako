package com.makobrothers.rrhh.persona.infrastructure.output.persistence

import com.makobrothers.shared.infrastructure.jdbc.Query
import com.makobrothers.rrhh.persona.domain.criteria.PersonaCriteria
import org.apache.commons.lang3.StringUtils
import org.apache.ibatis.jdbc.SQL

/**
 * Clase con las queries de la entidad Persona generadas con el MDA.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com)
 */
open class PersonaQueryGenerated : Query<PersonaCriteria> {
    override val namePrimaryKey: String = "ID_PERSONA"

    /**
     * Método encargado de generar la query de consulta el número de registros que cumplen
     * un determinado criterio de selección.
     *
     * @param criteria Critero de selección de registros.
     * @return String con la cadena SQL que contiene la consulta para los criterios
     * que se pasaron como parámetros.
     */
    override fun toSQLRowCount(criteria: PersonaCriteria): String {
        return toSQL(criteria, true)
    }

    /**
     * Método encargado de generar la query de consulta de n registros de tipo Persona que cumplen
     * un determinado criterio de selección.
     *
     * @param criteria Critero de selección de registros.
     * @return String con la cadena SQL que contiene la consulta para los criterios
     * que se pasaron como parámetros.
     */
    override fun toSQLFetchRows(criteria: PersonaCriteria): String {
       return toSQL(criteria)
    }

    private fun toSQL(criteria: PersonaCriteria, isSQLForCount: Boolean = false): String {
        var sql = SQL()
        sql = addSelectClause(sql, isSQLForCount)
        sql = addFromClause(sql)
        sql = addWhereClause(sql, criteria)
        sql = addOrderClause(sql, criteria, isSQLForCount)
        return sql.toString().uppercase()
    }

    private fun addSelectClause(sql: SQL, isSQLForCount: Boolean = false) : SQL {
        if (isSQLForCount) {
            return sql.SELECT("COUNT(1)")
        }
        return addSelectClauseAllFields(sql)
    }

    private fun addSelectClauseAllFields(sql: SQL): SQL {
        sql.SELECT("T0.ID_PERSONA AS T0ID_PERSONA")
        sql.SELECT("T0.VERSION AS T0VERSION")
        sql.SELECT("T0.NOM AS T0NOM")
        sql.SELECT("T0.PRIMER_COGNOM AS T0PRIMER_COGNOM")
        sql.SELECT("T0.SEGON_COGNOM AS T0SEGON_COGNOM")
        sql.SELECT("T0.NIF_NIE AS T0NIF_NIE")
        sql.SELECT("T0.ALIES AS T0ALIES")
        sql.SELECT("T0.ADRECA_CE AS T0ADRECA_CE")
        sql.SELECT("T0.MOBIL_PERSONAL AS T0MOBIL_PERSONAL")
        sql.SELECT("T0.RESPONSABLE_DIRECTE AS T0RESPONSABLE_DIRECTE")
        sql.SELECT("T0.DATA_INCORPORACIO AS T0DATA_INCORPORACIO")
        sql.SELECT("T0.DATA_PREVISTA_FINALITZACIO AS T0DATA_PREVISTA_FINALITZACIO")
        sql.SELECT("T0.DATA_NAIXEMENT AS T0DATA_NAIXEMENT")
        sql.SELECT("T0.DATA_BAIXA_EFECTIVA AS T0DATA_BAIXA_EFECTIVA")
        sql.SELECT("T0.CURRICULUM_VITAE AS T0CURRICULUM_VITAE")
        sql.SELECT("T0.FOTO AS T0FOTO")
        sql.SELECT("T0.ES_RESPONSABLE AS T0ES_RESPONSABLE")
        sql.SELECT("T0.ALTA_A_GID AS T0ALTA_A_GID")
        sql.SELECT("T0.EMAIL_GID AS T0EMAIL_GID")
        sql.SELECT("T0.ORGANITZACIO_GID AS T0ORGANITZACIO_GID")
        sql.SELECT("T0.DEPENDENCIA_GID AS T0DEPENDENCIA_GID")
        sql.SELECT("T0.IDENTIFICADOR_GID AS T0IDENTIFICADOR_GID")
        sql.SELECT("T0.GID6 AS T0GID6")
        sql.SELECT("T0.CREAT_PER AS T0CREAT_PER")
        sql.SELECT("T0.DATA_CREACIO AS T0DATA_CREACIO")
        sql.SELECT("T0.MODIFICAT_PER AS T0MODIFICAT_PER")
        sql.SELECT("T0.DATA_MODIFICACIO AS T0DATA_MODIFICACIO")
        sql.SELECT("T0.DATA_BAIXA AS T0DATA_BAIXA")
        return sql
    }

    private fun addFromClause(sql: SQL) : SQL {
        return sql.FROM("RRHH.PERSONES T0")
    }

    private fun addWhereClause(sql: SQL, criteria: PersonaCriteria): SQL {
        if (StringUtils.isNotEmpty(criteria.eqId)) {
            sql.WHERE("T0.ID_PERSONA = :EQ_ID_PERSONA")
        }
        if (StringUtils.isNotEmpty(criteria.ilikeNom)) {
            sql.WHERE("T0.NOM ILIKE :ILIKE_NOM")
        }
        if (StringUtils.isNotEmpty(criteria.ilikePrimerCognom)) {
            sql.WHERE("T0.PRIMER_COGNOM ILIKE :ILIKE_PRIMER_COGNOM")
        }
        if (StringUtils.isNotEmpty(criteria.ilikeSegonCognom)) {
            sql.WHERE("T0.SEGON_COGNOM ILIKE :ILIKE_SEGON_COGNOM")
        }
        if (StringUtils.isNotEmpty(criteria.ilikeNifNie)) {
            sql.WHERE("T0.NIF_NIE ILIKE :ILIKE_NIF_NIE")
        }
        if (StringUtils.isNotEmpty(criteria.ilikeAdrecaCE)) {
            sql.WHERE("T0.ADRECA_CE ILIKE :ILIKE_ADRECA_CE")
        }
        if (StringUtils.isNotEmpty(criteria.ilikeMobilPersonal)) {
            sql.WHERE("T0.MOBIL_PERSONAL ILIKE :ILIKE_MOBIL_PERSONAL")
        }
        if (StringUtils.isNotEmpty(criteria.eqResponsableDirecte)) {
            sql.WHERE("T0.RESPONSABLE_DIRECTE = :EQ_RESPONSABLE_DIRECTE")
        }
        if (criteria.geDataIncorporacio != null) {
            sql.WHERE("T0.DATA_INCORPORACIO >= :GE_DATA_INCORPORACIO")
        }
        if (criteria.leDataIncorporacio != null) {
            sql.WHERE("T0.DATA_INCORPORACIO <= :LE_DATA_INCORPORACIO")
        }
        if (criteria.eqDataIncorporacio != null) {
            sql.WHERE("T0.DATA_INCORPORACIO = :EQ_DATA_INCORPORACIO")
        }
        if (criteria.geDataPrevistaFinalitzacio != null) {
            sql.WHERE("T0.DATA_PREVISTA_FINALITZACIO >= :GE_DATA_PREVISTA_FINALITZACIO")
        }
        if (criteria.leDataPrevistaFinalitzacio != null) {
            sql.WHERE("T0.DATA_PREVISTA_FINALITZACIO <= :LE_DATA_PREVISTA_FINALITZACIO")
        }
        if (criteria.eqDataPrevistaFinalitzacio != null) {
            sql.WHERE("T0.DATA_PREVISTA_FINALITZACIO = :EQ_DATA_PREVISTA_FINALITZACIO")
        }
        if (criteria.geDataNaixement != null) {
            sql.WHERE("T0.DATA_NAIXEMENT >= :GE_DATA_NAIXEMENT")
        }
        if (criteria.leDataNaixement != null) {
            sql.WHERE("T0.DATA_NAIXEMENT <= :LE_DATA_NAIXEMENT")
        }
        if (criteria.eqDataNaixement != null) {
            sql.WHERE("T0.DATA_NAIXEMENT = :EQ_DATA_NAIXEMENT")
        }
        if (criteria.eqEsResponsable != null) {
            sql.WHERE("T0.ES_RESPONSABLE = :EQ_ES_RESPONSABLE")
        }
        if (criteria.eqAltaAGid != null) {
            sql.WHERE("T0.ALTA_A_GID = :EQ_ALTA_A_GID")
        }
        if (criteria.geDataCreacio != null) {
            sql.WHERE("T0.DATA_CREACIO >= :GE_DATA_CREACIO")
        }
        if (criteria.leDataCreacio != null) {
            sql.WHERE("T0.DATA_CREACIO <= :LE_DATA_CREACIO")
        }
        if (criteria.eqDataCreacio != null) {
            sql.WHERE("T0.DATA_CREACIO = :EQ_DATA_CREACIO")
        }
        if (criteria.geDataModificacio != null) {
            sql.WHERE("T0.DATA_MODIFICACIO >= :GE_DATA_MODIFICACIO")
        }
        if (criteria.leDataModificacio != null) {
            sql.WHERE("T0.DATA_MODIFICACIO <= :LE_DATA_MODIFICACIO")
        }
        if (criteria.eqDataModificacio != null) {
            sql.WHERE("T0.DATA_MODIFICACIO = :EQ_DATA_MODIFICACIO")
        }
        if (criteria.geDataBaixa != null) {
            sql.WHERE("T0.DATA_BAIXA >= :GE_DATA_BAIXA")
        }
        if (criteria.leDataBaixa != null) {
            sql.WHERE("T0.DATA_BAIXA <= :LE_DATA_BAIXA")
        }
        if (criteria.eqDataBaixa != null) {
            sql.WHERE("T0.DATA_BAIXA = :EQ_DATA_BAIXA")
        }
        return sql
    }

    private fun addOrderClause(sql: SQL, criteria: PersonaCriteria, isSQLForCount: Boolean = false): SQL {
        if (!isSQLForCount) {
            val orderBy = getOrderByClauseSQL(criteria)
            if (StringUtils.isNotEmpty(orderBy)) {
                sql.ORDER_BY(orderBy)
            }
        }
        return sql
    }

    /**
     * Claúsula "Order by" SQL para filtrar datos según los criterios.
     *
     * @return Retorna el contenido de una clásula order by para ordenar los resultados.
     */
    private fun getOrderByClauseSQL(criteria: PersonaCriteria): String {
        var orderBy = StringBuilder()
        if (StringUtils.isNotEmpty(criteria.orderBy)) {
            orderBy = StringBuilder(criteria.orderBy)
        }
        return orderBy.toString()
    }


    /**
     * Método encargado de generar la query de consulta de 1 registro de un tipo de objeto.
     *
     * @return String con la cadena SQL que contiene la consulta por id.
     */
    override fun toSQLFindById(): String {
        return findByIdQuery
    }

    /**
     * Método encargado de generar la query de inserción de un objeto de tipo
     * Persona
     *
     * @return String con la cadena SQL que contiene la sentencia INSERT.
     */
    override fun toSQLInsert(): String {
        return insertQuery
    }

    /**
     * Método encargado de generar la query de modificación de un objeto de tipo
     * Persona
     *
     * @return String con la cadena SQL que contiene la sentencia UPDATE.
     */
    override fun toSQLUpdate(): String {
        return updateQuery
    }

    /**
     * Método encargado de generar la query de borrado de un objeto de tipo
     * Persona por id.
     *
     * @return String con la cadena SQL que contiene la query de borrado filtrando por id. Sólo
     * se borra una instancia de objeto si es que existe.
     */
    override fun toSQLDeleteById(): String {
        return deleteByIdQuery
    }

    /**
     * Método encargado de generar la query de borrado de un objeto de tipo
     * Persona
     *
     * @param criteria Critero de selección de registros.
     * @return String con la cadena SQL que contiene la query de borrado para los criterios
     * que se pasaron como parámetros.
     */
    override fun toSQLDelete(criteria: PersonaCriteria): String {
        var sql = SQL()
        sql.DELETE_FROM("RRHH.PERSONES T0")
        sql = addWhereClause(sql, criteria)
        return sql.toString().uppercase()
    }

    companion object {
        private val findByIdQuery = SQL().SELECT("T0.ID_PERSONA AS T0ID_PERSONA")
                .SELECT("T0.VERSION AS T0VERSION")
                .SELECT("T0.NOM AS T0NOM")
                .SELECT("T0.PRIMER_COGNOM AS T0PRIMER_COGNOM")
                .SELECT("T0.SEGON_COGNOM AS T0SEGON_COGNOM")
                .SELECT("T0.NIF_NIE AS T0NIF_NIE")
                .SELECT("T0.ALIES AS T0ALIES")
                .SELECT("T0.ADRECA_CE AS T0ADRECA_CE")
                .SELECT("T0.MOBIL_PERSONAL AS T0MOBIL_PERSONAL")
                .SELECT("T0.RESPONSABLE_DIRECTE AS T0RESPONSABLE_DIRECTE")
                .SELECT("T0.DATA_INCORPORACIO AS T0DATA_INCORPORACIO")
                .SELECT("T0.DATA_PREVISTA_FINALITZACIO AS T0DATA_PREVISTA_FINALITZACIO")
                .SELECT("T0.DATA_NAIXEMENT AS T0DATA_NAIXEMENT")
                .SELECT("T0.DATA_BAIXA_EFECTIVA AS T0DATA_BAIXA_EFECTIVA")
                .SELECT("T0.CURRICULUM_VITAE AS T0CURRICULUM_VITAE")
                .SELECT("T0.FOTO AS T0FOTO")
                .SELECT("T0.ES_RESPONSABLE AS T0ES_RESPONSABLE")
                .SELECT("T0.ALTA_A_GID AS T0ALTA_A_GID")
                .SELECT("T0.EMAIL_GID AS T0EMAIL_GID")
                .SELECT("T0.ORGANITZACIO_GID AS T0ORGANITZACIO_GID")
                .SELECT("T0.DEPENDENCIA_GID AS T0DEPENDENCIA_GID")
                .SELECT("T0.IDENTIFICADOR_GID AS T0IDENTIFICADOR_GID")
                .SELECT("T0.GID6 AS T0GID6")
                .SELECT("T0.CREAT_PER AS T0CREAT_PER")
                .SELECT("T0.DATA_CREACIO AS T0DATA_CREACIO")
                .SELECT("T0.MODIFICAT_PER AS T0MODIFICAT_PER")
                .SELECT("T0.DATA_MODIFICACIO AS T0DATA_MODIFICACIO")
                .SELECT("T0.DATA_BAIXA AS T0DATA_BAIXA")
                .FROM("RRHH.PERSONES T0")
                .WHERE("T0.ID_PERSONA = :ID_PERSONA").toString().uppercase()
        private val insertQuery = SQL().INSERT_INTO("RRHH.PERSONES")
                .VALUES("ID_PERSONA", ":ID_PERSONA")
                .VALUES("VERSION", ":VERSION")
                .VALUES("NOM", ":NOM")
                .VALUES("PRIMER_COGNOM", ":PRIMER_COGNOM")
                .VALUES("SEGON_COGNOM", ":SEGON_COGNOM")
                .VALUES("NIF_NIE", ":NIF_NIE")
                .VALUES("ALIES", ":ALIES")
                .VALUES("ADRECA_CE", ":ADRECA_CE")
                .VALUES("MOBIL_PERSONAL", ":MOBIL_PERSONAL")
                .VALUES("RESPONSABLE_DIRECTE", ":RESPONSABLE_DIRECTE")
                .VALUES("DATA_INCORPORACIO", ":DATA_INCORPORACIO")
                .VALUES("DATA_PREVISTA_FINALITZACIO", ":DATA_PREVISTA_FINALITZACIO")
                .VALUES("DATA_NAIXEMENT", ":DATA_NAIXEMENT")
                .VALUES("DATA_BAIXA_EFECTIVA", ":DATA_BAIXA_EFECTIVA")
                .VALUES("CURRICULUM_VITAE", ":CURRICULUM_VITAE")
                .VALUES("FOTO", ":FOTO")
                .VALUES("ES_RESPONSABLE", ":ES_RESPONSABLE")
                .VALUES("ALTA_A_GID", ":ALTA_A_GID")
                .VALUES("EMAIL_GID", ":EMAIL_GID")
                .VALUES("ORGANITZACIO_GID", ":ORGANITZACIO_GID")
                .VALUES("DEPENDENCIA_GID", ":DEPENDENCIA_GID")
                .VALUES("IDENTIFICADOR_GID", ":IDENTIFICADOR_GID")
                .VALUES("GID6", ":GID6")
                .VALUES("CREAT_PER", ":CREAT_PER")
                .VALUES("DATA_CREACIO", ":DATA_CREACIO")
                .VALUES("MODIFICAT_PER", ":MODIFICAT_PER")
                .VALUES("DATA_MODIFICACIO", ":DATA_MODIFICACIO")
                .VALUES("DATA_BAIXA", ":DATA_BAIXA")
                .toString().uppercase()
        private val updateQuery = SQL().UPDATE("RRHH.PERSONES")
                .SET("VERSION = :VERSION + 1")
                .SET("NOM = :NOM")
                .SET("PRIMER_COGNOM = :PRIMER_COGNOM")
                .SET("SEGON_COGNOM = :SEGON_COGNOM")
                .SET("NIF_NIE = :NIF_NIE")
                .SET("ALIES = :ALIES")
                .SET("ADRECA_CE = :ADRECA_CE")
                .SET("MOBIL_PERSONAL = :MOBIL_PERSONAL")
                .SET("RESPONSABLE_DIRECTE = :RESPONSABLE_DIRECTE")
                .SET("DATA_INCORPORACIO = :DATA_INCORPORACIO")
                .SET("DATA_PREVISTA_FINALITZACIO = :DATA_PREVISTA_FINALITZACIO")
                .SET("DATA_NAIXEMENT = :DATA_NAIXEMENT")
                .SET("DATA_BAIXA_EFECTIVA = :DATA_BAIXA_EFECTIVA")
                .SET("CURRICULUM_VITAE = :CURRICULUM_VITAE")
                .SET("FOTO = :FOTO")
                .SET("ES_RESPONSABLE = :ES_RESPONSABLE")
                .SET("ALTA_A_GID = :ALTA_A_GID")
                .SET("EMAIL_GID = :EMAIL_GID")
                .SET("ORGANITZACIO_GID = :ORGANITZACIO_GID")
                .SET("DEPENDENCIA_GID = :DEPENDENCIA_GID")
                .SET("IDENTIFICADOR_GID = :IDENTIFICADOR_GID")
                .SET("GID6 = :GID6")
                .SET("CREAT_PER = :CREAT_PER")
                .SET("DATA_CREACIO = :DATA_CREACIO")
                .SET("MODIFICAT_PER = :MODIFICAT_PER")
                .SET("DATA_MODIFICACIO = :DATA_MODIFICACIO")
                .SET("DATA_BAIXA = :DATA_BAIXA")
                .WHERE("ID_PERSONA = :ID_PERSONA")
                .WHERE("VERSION = :VERSION")
                .toString().uppercase()
        private val deleteByIdQuery = SQL().DELETE_FROM("RRHH.PERSONES")
                .WHERE("ID_PERSONA = :ID_PERSONA")
                .toString().uppercase()
    }
}