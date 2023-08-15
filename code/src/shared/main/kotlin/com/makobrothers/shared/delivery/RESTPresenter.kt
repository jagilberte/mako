package com.makobrothers.shared.delivery

import com.makobrothers.shared.domain.boundary.provide.RESTResponseModel
import com.makobrothers.shared.dto.Page
import org.springframework.http.ResponseEntity

interface RESTPresenter<T> : Presenter<T> {

    fun generateResponse(): ResponseEntity<T>

    fun generateResponseWithValidations(): ResponseEntity<RESTResponseModel<T>>

    fun generateResponseList(): ResponseEntity<List<T>>

    fun generateResponsePage(): ResponseEntity<Page<T>>

    fun generateResponseDelete(): ResponseEntity<Int>

}