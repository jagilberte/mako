package com.makobrothers.rrhh.persona.infrastructure.input.rest

import com.makobrothers.shared.commons.dto.Page

import com.makobrothers.rrhh.persona.application.port.input.*
import com.makobrothers.rrhh.persona.domain.entities.Persona
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
class PersonaFindPageByCriteriaRest(@Inject val service: PersonaFindPageByCriteriaUseCase) {

    @Operation(summary = "Find personas by a criteria returning paginated result")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found a list of persona", content = [(Content(mediaType = "application/json", 
            array = (ArraySchema(schema = Schema(implementation = Persona::class)))))])]
    )
    @GetMapping("/v1/findByPageCriteria")
    fun findPageByCriteria(personaCriteriaRequestModel: PersonaCriteriaPagedRequestModel): ResponseEntity<Page<Persona>> {
        val presenter = PersonaRestPresenter()
        service.execute(personaCriteriaRequestModel, presenter)        
        return presenter.generateResponsePage()
    }

}