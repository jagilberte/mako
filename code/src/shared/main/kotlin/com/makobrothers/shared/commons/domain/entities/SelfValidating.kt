package com.makobrothers.shared.commons.domain.entities

import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator

abstract class SelfValidating<T> {
  private val validator: Validator

  init {
    val factory = Validation.buildDefaultValidatorFactory()
    validator = factory.validator
  }

  fun validateSelf(): Set<ConstraintViolation<T>> {
    return validator.validate(this as T)
  }
}