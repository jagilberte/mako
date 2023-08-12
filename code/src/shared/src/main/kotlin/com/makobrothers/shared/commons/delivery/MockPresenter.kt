package com.makobrothers.shared.commons.delivery

import com.makobrothers.shared.commons.domain.boundary.provide.RESTResponseModel
import com.makobrothers.shared.commons.dto.Page

interface MockPresenter<T> : Presenter<T> {

    fun generateResponse(): MockResponseEntity<T>

    fun generateResponseWithValidations(): MockResponseEntity<RESTResponseModel<T>>

    fun generateResponseList(): MockResponseEntity<List<T>>

    fun generateResponsePage(): MockResponseEntity<Page<T>>

    fun generateResponseDelete(): MockResponseEntity<Int>

}