package com.makobrothers.ddd.commons.domain.boundary.provide

import java.util.*
import jakarta.validation.ConstraintViolation

/**
 * Bean que define los datos de una RESTResponseModel.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 *
 */
data class RESTResponseModel<T>(var entity: T,
    var validations: Optional<Set<ConstraintViolation<T>>>)