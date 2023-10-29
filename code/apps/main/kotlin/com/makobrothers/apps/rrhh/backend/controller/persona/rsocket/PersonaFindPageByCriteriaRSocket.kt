package com.makobrothers.apps.rrhh.backend.controller.persona.rsocket

import com.makobrothers.shared.dto.Page
import com.makobrothers.rrhh.persona.application.port.input.*
import com.makobrothers.rrhh.persona.domain.entities.Persona

import javax.inject.Inject
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

import org.slf4j.LoggerFactory

@Controller
@MessageMapping("persona.")
class PersonaFindPageByCriteriaRSocket(@Inject val service: PersonaFindPageByCriteriaUseCase) {
    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("v1.findPageByCriteria")
    fun findPageByCriteria(personaCriteriaRequestModel: PersonaCriteriaPagedRequestModel): ResponseEntity<Page<Persona>> {
        val presenter = PersonaRSocketPresenter()
        service.execute(personaCriteriaRequestModel, presenter)
        return presenter.generateResponsePage()
    }

}