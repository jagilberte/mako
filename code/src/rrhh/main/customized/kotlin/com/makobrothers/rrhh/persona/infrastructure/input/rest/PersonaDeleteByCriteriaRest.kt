package com.makobrothers.rrhh.persona.infrastructure.input.rest

import com.makobrothers.rrhh.persona.application.port.input.*

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
class PersonaDeleteByCriteriaRest(@Inject val service: PersonaDeleteByCriteriaUseCase) {

    @Operation(summary = "Delete existing personas by criteria")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Deleted the collection of persona", content = [(Content(mediaType = "application/json", 
            schema = Schema(implementation = Integer::class)))])]
    )
    @DeleteMapping("/v1/deleteByCriteria")
    fun deleteByCriteria(personaCriteriaRequestModel: PersonaCriteriaRequestModel): ResponseEntity<Int> {
        val presenter = PersonaRestPresenter()
        service.execute(personaCriteriaRequestModel, presenter)
        return presenter.generateResponseDelete()
    }

}