package com.makobrothers.shared.commons.infrastructure.repository

import com.makobrothers.shared.commons.dto.Page

/**
 * Plantilla genérica para los Repository. Para no repetir los Repository's utilizamos
 * generics de java. Ya que todas los Repository's a priori hacen las mismas
 * operaciones.

 * @param T Clase de los objetos a consultar en BD.
 * *
 * @param PK Tipo de la primary key.
 * *
 * @param C Criteria para montar la cláusula where de las queries.
 * *
 * *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
interface RepositoryView<T, in PK, in C> {

    /**
     * Obtiene un objeto T de la base de datos

     * @param id El identificador de la entidad T
     * *
     * @return La entidad T
     */
    fun findById(id: PK): T?

    /**
     * Devuelve todos los objetos de tipo T que cumplan los criterios y el filtro si los tienen

     * @param criteria Dto con criterios de busqueda
     * *
     * @return List con todos los objetos de tipo T que cumplen los criterios
     */
    fun findByCriteria(criteria: C): List<T>

    /**
     * Devuelve todas los objetos de tipo T que cumplan los criterios y el filtro si los tienen
     * en un objeto Page.

     * @param criteria Dto con criterios de búsqueda
     * *
     * @return Page con todas las entidades tipo T y contadores que cumplen el criterio.
     */
    fun findPageByCriteria(criteria: C): Page<T>
}