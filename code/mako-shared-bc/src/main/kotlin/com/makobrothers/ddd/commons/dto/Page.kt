package com.makobrothers.ddd.commons.dto

import java.io.Serializable
import java.util.*

/**
 * Bean parametrizado utilizado para devolver resultados paginados de una
 * consulta a la BD. Es una implementación de la interficie
 * org.springframework.data.domain.Page de Spring Data.
 *
 * @param  T Clase de objetos que se retornan en la lista.
 * @author José-Alberto Gilberte León (jagilberte).
 */
class Page<T>(var size: Int = 0, var number: Int = 0, var totalPages: Int = 0) : Serializable {
    var totalElements: Long = 0
    var content: List<T> = Collections.emptyList()

    val numberOfElements: Int
        get() {
            var numberOfElements = 0
            if (isHasContent)
                numberOfElements = content.size
            return numberOfElements
        }

    val isHasPreviousPage: Boolean
        get() {
            var bIsHasPreviousPage = false
            if (totalPages > 0 && number != 1)
                bIsHasPreviousPage = true
            return bIsHasPreviousPage
        }

    val isFirstPage: Boolean
        get() {
            var bIsFirstPage = false
            if (number == 1)
                bIsFirstPage = true
            return bIsFirstPage
        }

    val isHasNextPage: Boolean
        get() {
            var bHasNextPage = false
            if (number != totalPages && totalPages != 1)
                bHasNextPage = true
            return bHasNextPage
        }

    val isLastPage: Boolean
        get() {
            var bIsLastPage = false
            if (number == totalPages)
                bIsLastPage = true
            return bIsLastPage
        }

    private val isHasContent: Boolean
        get() {
            var bIsHasContent = false
            if (content.isNotEmpty()) {
                bIsHasContent = true
            }
            return bIsHasContent
        }

    companion object {
        internal const val serialVersionUID = 0L
    }
}