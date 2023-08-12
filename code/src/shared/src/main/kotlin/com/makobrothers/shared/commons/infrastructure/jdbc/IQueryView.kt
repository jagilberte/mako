package com.makobrothers.shared.commons.infrastructure.jdbc

/**
 * Interficie que define los métodos básicos de un objeto QueryView.

 * @param C Tipo de criteria.
 * *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
interface QueryView<in C> {

    /**
     * Retorna el camp que hace de primary key.

     * @return String con el nombre del campo que es primary key.
     */
    val namePrimaryKey: String

    /**
     * Método encargado de generar la query de conteo de registros que cumplen
     * un determinado criterio de selección.

     * @param criteria Critero de selección de registros.
     * *
     * @return String con la cadena SQL que contiene la consulta para los criterios
     * * que se pasaron como parámetros.
     */
    fun toSQLRowCount(criteria: C): String

    /**
     * Método encargado de generar la query de consulta de n registros que cumplen
     * un determinado criterio de selección.

     * @param criteria Critero de selección de registros.
     * *
     * @return String con la cadena SQL que contiene la consulta para los criterios
     * * que se pasaron como parámetros.
     */
    fun toSQLFetchRows(criteria: C): String

    /**
     * Método encargado de generar la query de consulta de un objeto.

     * @return String con la cadena SQL que contiene la sentencia SELECT por clave primaria.
     */
    fun toSQLFindById(): String
}