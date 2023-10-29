package com.makobrothers.apps.rrhh.backend.controller.persona.rest

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
class PersonaFindByIdRest(@Inject val service: PersonaFindByIdUseCase) {

    @Operation(summary = "Find a persona by its id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found persona", content = [(Content(mediaType = "application/json", 
            schema = Schema(implementation = Persona::class)))]),
        ApiResponse(responseCode = "404", description = "Did not find any persona", content = [Content()])]
    )
    @GetMapping("/v1/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<Persona> {
        val presenter = PersonaRestPresenter()
        service.execute(id, presenter)
        return presenter.generateResponse()
    }

}