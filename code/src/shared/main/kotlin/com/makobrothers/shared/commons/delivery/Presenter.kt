package com.makobrothers.shared.commons.delivery

import com.makobrothers.shared.commons.domain.boundary.MessageCode
import com.makobrothers.shared.commons.dto.Page
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