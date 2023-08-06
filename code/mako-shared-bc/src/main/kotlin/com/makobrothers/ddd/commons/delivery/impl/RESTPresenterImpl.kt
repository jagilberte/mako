package com.makobrothers.ddd.commons.delivery.impl

import com.makobrothers.ddd.commons.delivery.RESTPresenter
import com.makobrothers.ddd.commons.domain.boundary.MessageCode
import com.makobrothers.ddd.commons.domain.boundary.provide.RESTResponseModel
import com.makobrothers.ddd.commons.dto.Page
import org.slf4j.*
import org.springframework.http.*
import java.lang.reflect.ParameterizedType
import java.util.*
import jakarta.validation.ConstraintViolation

open class RESTPresenterImpl<T> : RESTPresenter<T> {
    private val log: Logger = LoggerFactory.getLogger("RESTPresenterImpl")
    private var typeClass: Class<T>? = null
    private var response: RESTResponseModel<T>? = null
    private var list: List<T> = Collections.emptyList()
    private var page: Page<T> = Page()
    private var serverErrorCode: MessageCode? = null
    private var clientErrorCode: MessageCode? = null
    private var deleted: Int = 0
    private var isEntityFound: Boolean = false
    private var exception: Exception? = null

    init {
        val genericClass = getGenericClass()
        val parameterizedType = genericClass.genericSuperclass as ParameterizedType
        typeClass = parameterizedType.actualTypeArguments[TYPE_T_CLASS_INDEX] as Class<T>
        response = RESTResponseModel(typeClass!!.getDeclaredConstructor().newInstance(), Optional.empty())
    }

    private fun getGenericClass(): Class<*> {
        var genericClass: Class<*> = javaClass
        while (RESTPresenter::class.java.simpleName != genericClass.superclass.simpleName) {
            if (genericClass.genericSuperclass is ParameterizedType)
                break
            genericClass = genericClass.superclass
        }
        return genericClass
    }

    override fun generateResponse(): ResponseEntity<T> {
        return when {
            noErrors() -> generateResponseNoErrors()
            else -> ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    private fun generateResponseNoErrors(): ResponseEntity<T> {
        return when {
            isEntityFound -> ResponseEntity(response?.entity, HttpStatus.OK) 
            else -> ResponseEntity(HttpStatus.NOT_FOUND) 
        }
    }

    override fun generateResponseWithValidations(): ResponseEntity<RESTResponseModel<T>> {
        return when {
            haveErrors() -> ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            validationsNotEmpty() -> ResponseEntity(response!!, HttpStatus.NOT_ACCEPTABLE)
            else -> ResponseEntity(response!!, HttpStatus.OK)
        }
    }

    override fun generateResponseList(): ResponseEntity<List<T>> {
        return if (haveErrors()) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } else {
            ResponseEntity(list, HttpStatus.OK)
        }
    }

    override fun generateResponsePage(): ResponseEntity<Page<T>> {
        return if (haveErrors()) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } else {
            ResponseEntity(page, HttpStatus.OK)
        }
    }

    override fun generateResponseDelete(): ResponseEntity<Int> {
        return if (haveErrors()) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } else {
            ResponseEntity(deleted, HttpStatus.OK)
        }
    }

    private fun validationsNotEmpty(): Boolean {
        return response!!.validations.isPresent
    }

    private fun noErrors() = !haveErrors()

    private fun haveErrors() = serverErrorCode != null

    override fun sendResponse(entity : T) {
        this.response!!.entity = entity
        isEntityFound = true
    }

    override fun sendValidations(validations: Set<ConstraintViolation<T>>) {
        this.response?.validations = Optional.of(validations)
    }

    override fun sendResponse(list: List<T>) {
        this.list = list
    }

    override fun sendResponse(page: Page<T>) {
        this.page = page
    }

    override fun sendResponse(deleted: Int) {
        this.deleted = deleted
    }

    override fun sendServerErrorMessage(code: MessageCode, exception: Exception) {
        this.serverErrorCode = code
        this.exception = exception
        log.debug("[ServerErrorCode: $code, Exception $exception]")
    }

    override fun sendClientErrorMessage(code: MessageCode) {
        this.clientErrorCode = code
        log.debug("[ClientErrorCode: $code]")
    }

    companion object {
        private const val TYPE_T_CLASS_INDEX = 0
    }
}