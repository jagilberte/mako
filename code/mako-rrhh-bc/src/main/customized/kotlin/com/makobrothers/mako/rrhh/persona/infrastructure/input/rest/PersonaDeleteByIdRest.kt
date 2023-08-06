package com.makobrothers.mako.rrhh.persona.infrastructure.input.rest

import com.makobrothers.mako.rrhh.persona.application.port.input.*

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
class PersonaDeleteByIdRest(@Inject val service: PersonaDeleteByIdUseCase) {

    @Operation(summary = "Delete existing persona by its id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Deleted the persona", content = [(Content(mediaType = "application/json", 
            schema = Schema(implementation = Integer::class)))])]
    )
    @DeleteMapping("/v1/{id}")
    fun deleteById(@PathVariable id: String): ResponseEntity<Int> {
        val presenter = PersonaRestPresenter()
        service.execute(id, presenter)
        return presenter.generateResponseDelete()
    }

}