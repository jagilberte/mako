package com.makobrothers.mako.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaFindByIdRSocket(@Inject val service: PersonaFindByIdUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("v1.findById.{id}")
    fun findById(@DestinationVariable id: String): ResponseEntity<Persona> {
        val presenter = PersonaRSocketPresenter()
        service.execute(id, presenter)
        return presenter.generateResponse()
    }

}