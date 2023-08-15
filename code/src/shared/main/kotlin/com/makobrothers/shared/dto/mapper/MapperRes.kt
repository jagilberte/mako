package com.makobrothers.shared.dto.mapper

/**
 * Resultado de un mapper: un dto y un prefijo. Necesario
 * para poder cualificar todos los campos de la select con
 * sus respectivas joins.
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
class MapperRes<T> {
    var dto: T? = null
    var prefix: Int = 0
}