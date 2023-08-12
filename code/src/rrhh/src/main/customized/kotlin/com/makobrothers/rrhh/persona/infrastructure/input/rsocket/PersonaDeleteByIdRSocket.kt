package com.makobrothers.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.rrhh.persona.application.port.input.*

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaDeleteByIdRSocket(@Inject val service: PersonaDeleteByIdUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("delete.{id}")
    fun deleteById(@DestinationVariable id: String): ResponseEntity<Int> {
        val presenter = PersonaRSocketPresenter()
        service.execute(id, presenter)
        return presenter.generateResponseDelete()
    }

}