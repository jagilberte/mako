package com.makobrothers.shared.delivery

data class MockResponseEntity<T>(val entity : T? = null, val status : String? = "OK") {

    fun getStatusCode() = status

    fun getBody() : T? = entity

    companion object {
        private const val TYPE_T_CLASS_INDEX = 0
        const val OK = "OK"
        const val NOT_ACCEPTABLE = "NOT_ACCEPTABLE"
        const val INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR"
    }
}