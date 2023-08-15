package com.makobrothers.rrhh.persona.infrastructure.input.rest

import com.makobrothers.shared.commons.domain.boundary.provide.RESTResponseModel

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
class PersonaInsertRest(@Inject val service: PersonaInsertUseCase) {

    @Operation(summary = "Create new persona")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Created new persona", content = [(Content(mediaType = "application/json", 
            schema = Schema(implementation = Persona::class)))]),
        ApiResponse(responseCode = "406", description = "The are validation errors", content = [Content()])]
    )
    @PostMapping("/v1")
    fun insert(@RequestBody personaRequestModel: PersonaRequestModel): ResponseEntity<RESTResponseModel<Persona>> {
        val presenter = PersonaRestPresenter()
        service.execute(personaRequestModel, presenter)
        return presenter.generateResponseWithValidations()
    }

}