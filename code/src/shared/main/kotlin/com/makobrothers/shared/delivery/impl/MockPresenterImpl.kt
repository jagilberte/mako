package com.makobrothers.shared.delivery.impl

import com.makobrothers.shared.delivery.*
import com.makobrothers.shared.domain.boundary.MessageCode
import com.makobrothers.shared.domain.boundary.provide.RESTResponseModel
import com.makobrothers.shared.dto.Page
import org.slf4j.*
import java.lang.reflect.ParameterizedType
import java.util.*
import jakarta.validation.ConstraintViolation

open class MockPresenterImpl<T> : MockPresenter<T> {
    private val log: Logger = LoggerFactory.getLogger("MockPresenterImpl")
    private var typeClass: Class<T>? = null
    private var response: RESTResponseModel<T>? = null
    protected var list: List<T> = Collections.emptyList()
    private var page: Page<T> = Page()
    private var serverErrorCode: MessageCode? = null
    private var clientErrorCode: MessageCode? = null
    private var deleted: Int = 0
    protected var exception: Exception? = null

    init {
        val genericClass = getGenericClass()
        val parameterizedType = genericClass.genericSuperclass as ParameterizedType
        typeClass = parameterizedType.actualTypeArguments[TYPE_T_CLASS_INDEX] as Class<T>
        response = RESTResponseModel(typeClass!!.getDeclaredConstructor().newInstance(), Optional.empty())
    }

    private fun getGenericClass(): Class<*> {
        var genericClass: Class<*> = javaClass
        while (MockPresenter::class.java.simpleName != genericClass.superclass.simpleName) {
            if (genericClass.genericSuperclass is ParameterizedType)
                break
            genericClass = genericClass.superclass
        }
        return genericClass
    }

    override fun generateResponse(): MockResponseEntity<T> {
        return when {
            noErrors() -> MockResponseEntity(response!!.entity)
            else -> MockResponseEntity(status = MockResponseEntity.INTERNAL_SERVER_ERROR)
        }
    }

    override fun generateResponseWithValidations(): MockResponseEntity<RESTResponseModel<T>> {
        return when {
            haveErrors() -> MockResponseEntity(status = MockResponseEntity.INTERNAL_SERVER_ERROR)
            validationsNotEmpty() -> MockResponseEntity(response!!, status = MockResponseEntity.NOT_ACCEPTABLE)
            else -> MockResponseEntity(response!!, status = MockResponseEntity.OK)
        }
    }

    override fun generateResponseList(): MockResponseEntity<List<T>> {
        return if (haveErrors()) {
            MockResponseEntity(status = MockResponseEntity.INTERNAL_SERVER_ERROR)
        } else {
            MockResponseEntity(list, status = MockResponseEntity.OK)
        }
    }

    override fun generateResponsePage(): MockResponseEntity<Page<T>> {
        return if (haveErrors()) {
            MockResponseEntity(status = MockResponseEntity.INTERNAL_SERVER_ERROR)
        } else {
            MockResponseEntity(page, status = MockResponseEntity.OK)
        }
    }

    override fun generateResponseDelete(): MockResponseEntity<Int> {
        return if (haveErrors()) {
            MockResponseEntity(status = MockResponseEntity.INTERNAL_SERVER_ERROR)
        } else {
            MockResponseEntity(deleted, status =MockResponseEntity.OK)
        }
    }

    private fun validationsNotEmpty(): Boolean {
        return response!!.validations!!.isPresent
    }

    private fun noErrors() = !haveErrors()

    private fun haveErrors() = serverErrorCode != null

    override fun sendResponse(entity : T) {
        this.response!!.entity = entity
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