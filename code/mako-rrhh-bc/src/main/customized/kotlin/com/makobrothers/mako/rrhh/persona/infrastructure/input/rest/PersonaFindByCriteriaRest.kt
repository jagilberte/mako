package com.makobrothers.mako.rrhh.persona.infrastructure.input.rest

import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.MediaType
import javax.inject.Inject
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.*
import io.swagger.v3.oas.annotations.security.SecurityRequirement

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/api/rest/personas", produces = [MediaType.APPLICATION_JSON_VALUE])
class PersonaFindByCriteriaRest(@Inject val service: PersonaFindByCriteriaUseCase) {

    @Operation(summary = "Find personas by a criteria returning all results")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found a list of persona", content = [(Content(mediaType = "application/json", 
            array = (ArraySchema(schema = Schema(implementation = Persona::class)))))])]
    )
    @GetMapping("/v1/findByCriteria")
    fun findByCriteria(personaCriteriaRequestModel: PersonaCriteriaRequestModel): ResponseEntity<List<Persona>> {
        val presenter = PersonaRestPresenter()
        service.execute(personaCriteriaRequestModel, presenter)
        return presenter.generateResponseList()
    }

}