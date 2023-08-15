package com.makobrothers.shared.commons.infrastructure.repository

/**
 * Plantilla genérica para los Repository. Para no repetir los Repository's utilizamos
 * generics de java. Ya que todas los Repository's a priori hacen las mismas
 * operaciones.

 * @param T Clase de los objetos a persistir/manipular en BD.
 * *
 * @param PK Tipo de la primary key.
 * *
 * @param C Criteria para montar la cláusula where de las queries.
 * *
 * *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
interface Repository<T, in PK, in C> : RepositoryView<T, PK, C> {
    /**
     * Inserta un objeto T

     * @param  dto Un dto de la entidad T
     * *
     * @return La entidad T.
     */
    fun insert(dto: T): T

    /**
     * Actualiza un objeto T

     * @param dto Un dto de la entidad T
     * *
     * @return La entidad T.
     */
    fun update(dto: T): T

    /**
     * Elimina un objeto T

     * @param id del dto de tipo T
     * *
     * @return Número de filas borradas
     */
    fun delete(id: PK): Int

    /**
     * Elimina n objetos T por Criteria.

     * @param criteria Criteria para borrar
     * *
     * @return Número de filas borradas
     */
    fun deleteByCriteria(criteria: C): Int
}