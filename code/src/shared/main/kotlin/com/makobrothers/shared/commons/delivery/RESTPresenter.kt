package com.makobrothers.shared.commons.delivery

import com.makobrothers.shared.commons.domain.boundary.provide.RESTResponseModel
import com.makobrothers.shared.commons.dto.Page
import org.springframework.http.ResponseEntity

interface RESTPresenter<T> : Presenter<T> {

    fun generateResponse(): ResponseEntity<T>

    fun generateResponseWithValidations(): ResponseEntity<RESTResponseModel<T>>

    fun generateResponseList(): ResponseEntity<List<T>>

    fun generateResponsePage(): ResponseEntity<Page<T>>

    fun generateResponseDelete(): ResponseEntity<Int>

}