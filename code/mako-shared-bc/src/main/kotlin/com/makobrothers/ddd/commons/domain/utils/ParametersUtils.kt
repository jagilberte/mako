package com.makobrothers.ddd.commons.domain.utils

import java.util.Date

class ParametersUtils {
    companion object {
        fun addStringParameter(parameters: MutableMap<String, Any>, key: String, value: String?) {
            if (!value.isNullOrEmpty()) {
                parameters.put(key, value)
            }
        }

        fun addIntParameter(parameters: MutableMap<String, Any>, key: String, value: Int?) {
            if (value != null) {
                parameters.put(key, value)
            }
        }

        fun addFloatParameter(parameters: MutableMap<String, Any>, key: String, value: Float?) {
            if (value != null) {
                parameters.put(key, value)
            }
        }

        fun addDoubleParameter(parameters: MutableMap<String, Any>, key: String, value: Double?) {
            if (value != null) {
                parameters.put(key, value)
            }
        }

        fun addBooleanParameter(parameters: MutableMap<String, Any>, key: String, value: Boolean?) {
            if (value != null) {
                parameters.put(key, value)
            }
        }

        fun addDateParameter(parameters: MutableMap<String, Any>, key: String, value: Date?) {
            if (value != null) {
                parameters.put(key, value)
            }
        }

        fun addILikeParameter(parameters: MutableMap<String, Any>, key: String, value: String?) {
            if (!value.isNullOrEmpty()) {
                parameters.put(key, "%$value%")
            }
        }
    }
}