package com.makobrothers.apps.rrhh.backend.controller.persona.rsocket

import com.makobrothers.rrhh.persona.application.port.input.*

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaDeleteByCriteriaRSocket(@Inject val service: PersonaDeleteByCriteriaUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("delete.{id}")
    fun deleteByCriteria(@DestinationVariable personaCriteriaRequestModel: PersonaCriteriaRequestModel): ResponseEntity<Int> {
        val presenter = PersonaRSocketPresenter()
        service.execute(personaCriteriaRequestModel, presenter)
        return presenter.generateResponseDelete()
    }

}