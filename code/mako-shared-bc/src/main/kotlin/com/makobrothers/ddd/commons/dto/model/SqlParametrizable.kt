package com.makobrothers.ddd.commons.dto.model

interface SqlParametrizable<PK> {
    var id: PK?
    var version: Int

    fun toMapSqlParameterSource(): MutableMap<String, Any?>
}