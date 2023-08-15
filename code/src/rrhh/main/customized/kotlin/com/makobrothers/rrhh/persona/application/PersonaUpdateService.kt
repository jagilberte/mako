package com.makobrothers.rrhh.persona.application

import com.makobrothers.shared.domain.boundary.MessageCode

import com.makobrothers.rrhh.commons.application.port.output.AdapterFactory
import com.makobrothers.rrhh.persona.application.port.input.PersonaUpdateUseCase
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.application.port.input.PersonaRequestModel

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("personaUpdateService")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER_MOD')")
class PersonaUpdateService(private val adapterFactory: AdapterFactory) : PersonaUpdateUseCase {
    private final val log = LoggerFactory.getLogger(javaClass)
    private final val personaRepository = adapterFactory.getPersonaRepository()

    override fun execute(requestModel: PersonaRequestModel, presenter: PersonaPresenter) {
        try {
            executeWithoutHandleExceptions(requestModel, presenter)
        } catch (e: Exception) {
            log.error(e.message, e)
            presenter.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR, e)
        }
    }

    @Transactional
    private fun executeWithoutHandleExceptions(requestModel: PersonaRequestModel, presenter: PersonaPresenter) {
        var persona = requestModel.toPersona()
        val validationErrors = persona.validateSelf()
        if (validationErrors.isEmpty()) {
            persona = personaRepository.update(persona)
            presenter.sendResponse(persona)
        } else {
            presenter.sendValidations(validationErrors)
        }
    }
}
