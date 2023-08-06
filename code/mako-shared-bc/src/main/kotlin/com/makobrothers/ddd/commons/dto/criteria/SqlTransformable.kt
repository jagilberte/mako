package com.makobrothers.ddd.commons.dto.criteria

interface SqlTransformable {
    val mapSQLParameterSource: MutableMap<String, Any>
    val actualPage: Int
    val pageSize: Int
    var totalPages: Int
}