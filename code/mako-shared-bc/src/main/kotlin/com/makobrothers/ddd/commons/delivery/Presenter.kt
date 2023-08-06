package com.makobrothers.ddd.commons.delivery

import com.makobrothers.ddd.commons.domain.boundary.MessageCode
import com.makobrothers.ddd.commons.dto.Page
import jakarta.validation.ConstraintViolation

interface Presenter<T> {

    fun sendResponse(entity: T)

    fun sendValidations(validations: Set<ConstraintViolation<T>>)

    fun sendResponse(list: List<T>)

    fun sendResponse(page: Page<T>)

    fun sendResponse(deleted: Int)

    fun sendServerErrorMessage(code: MessageCode, exception: Exception)

    fun sendClientErrorMessage(code: MessageCode)
}