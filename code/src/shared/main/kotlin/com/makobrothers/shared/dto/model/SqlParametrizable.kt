package com.makobrothers.shared.dto.model

interface SqlParametrizable<PK> {
    var id: PK?
    var version: Int

    fun toMapSqlParameterSource(): MutableMap<String, Any?>
}