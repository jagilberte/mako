package com.makobrothers.shared.domain.boundary.provide

import java.util.*
import jakarta.validation.ConstraintViolation

/**
 * Bean que define los datos de una RSocketResponseModel.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 *
 */
data class RSocketResponseModel<T>(var entity: T,
    var validations: Optional<Set<ConstraintViolation<T>>>)