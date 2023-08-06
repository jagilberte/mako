package com.makobrothers.ddd.commons.delivery

import com.makobrothers.ddd.commons.domain.boundary.provide.RESTResponseModel
import com.makobrothers.ddd.commons.dto.Page

interface MockPresenter<T> : Presenter<T> {

    fun generateResponse(): MockResponseEntity<T>

    fun generateResponseWithValidations(): MockResponseEntity<RESTResponseModel<T>>

    fun generateResponseList(): MockResponseEntity<List<T>>

    fun generateResponsePage(): MockResponseEntity<Page<T>>

    fun generateResponseDelete(): MockResponseEntity<Int>

}