package com.makobrothers.shared.commons.dto.criteria

import java.io.Serializable

/**
 * Clase de la que extienden todos los criteria. Filtros de seguridad,
 * contadores, información para ordenar e información para paginación.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
open class BaseCriteria : Serializable, SqlTransformable {
    private var defaultFilter: List<*>? = null
    var orderBy: String? = null
    override var actualPage: Int = 1
    override var pageSize: Int = 10
    override var totalPages: Int = 0
    private var isWithPageCount: Boolean = false

    override val mapSQLParameterSource: MutableMap<String, Any>
        get() =  LinkedHashMap<String, Any>()

    fun toOrderByClauseSQL(): String? {
        return null
    }

    companion object {
        internal const val serialVersionUID = 1L
    }
}