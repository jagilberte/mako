package com.makobrothers.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.shared.domain.boundary.provide.RESTResponseModel
import com.makobrothers.rrhh.persona.application.port.input.*
import com.makobrothers.rrhh.persona.domain.entities.Persona

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaUpdateRSocket(@Inject val service: PersonaUpdateUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("update") 
    fun update(personaRequestModel: PersonaRequestModel): ResponseEntity<RESTResponseModel<Persona>> {
        val presenter = PersonaRSocketPresenter()
        service.execute(personaRequestModel, presenter)
        return presenter.generateResponseWithValidations()
    }

}