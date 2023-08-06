package com.makobrothers.mako.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaFindByCriteriaRSocket(@Inject val service: PersonaFindByCriteriaUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("v1.findByCriteria")
    fun findByCriteria(personaCriteriaRequestModel: PersonaCriteriaRequestModel): ResponseEntity<List<Persona>> {
        val presenter = PersonaRSocketPresenter()
        service.execute(personaCriteriaRequestModel, presenter)
        return presenter.generateResponseList()
    }

}