package com.makobrothers.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.shared.commons.domain.boundary.provide.RESTResponseModel
import com.makobrothers.rrhh.persona.application.port.input.*
import com.makobrothers.rrhh.persona.domain.entities.Persona

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaInsertRSocket(@Inject val service: PersonaInsertUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("v1.insert") 
    fun insert(personaRequestModel: PersonaRequestModel): ResponseEntity<RESTResponseModel<Persona>> {
        val presenter = PersonaRSocketPresenter()
        service.execute(personaRequestModel, presenter)
        return presenter.generateResponseWithValidations()
    }

}