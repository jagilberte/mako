package com.makobrothers.shared.infrastructure.jdbc

/**
 * Interficie que define los métodos básicos de un objeto Query.

 * @param C Tipo de criteria.
 * *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
interface Query<in C> : QueryView<C> {
    /**
     * Método encargado de generar la query de inserción de un objeto.

     * @return String con la cadena SQL que contiene la sentencia INSERT.
     */
    fun toSQLInsert(): String

    /**
     * Método encargado de generar la query de modificación de un objeto.

     * @return String con la cadena SQL que contiene la sentencia UPDATE.
     */
    fun toSQLUpdate(): String

    /**
     * Método encargado de generar la query de borrado de un objeto por id.

     * @return String con la cadena SQL que contiene la query de borrado filtrando por id. Sólo
     * * se borra una instancia de objeto si es que existe.
     */
    fun toSQLDeleteById(): String

    /**
     * Método encargado de generar la query de borrado de un objeto filtrando por criterios.

     * @param criteria Critero de selección de registros a borrar.
     * *
     * @return String con la cadena SQL que contiene la query de borrado para los criterios
     * * que se pasaron como parámetros.
     */
    fun toSQLDelete(criteria: C): String
}