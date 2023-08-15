package com.makobrothers.rrhh.persona.application

import com.makobrothers.shared.commons.domain.boundary.MessageCode

import com.makobrothers.rrhh.commons.application.port.output.AdapterFactory
import com.makobrothers.rrhh.persona.application.port.input.PersonaDeleteByIdUseCase
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("personaDeleteByIdService")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER_MOD')")
class PersonaDeleteByIdService(private val adapterFactory: AdapterFactory) : PersonaDeleteByIdUseCase {

    private final val log = LoggerFactory.getLogger(javaClass)
    private final val personaRepository = adapterFactory.getPersonaRepository()

    override fun execute(id: String, presenter: PersonaPresenter) {
        try {
            executeWithoutHandleExceptions(id, presenter)
        } catch (e: Exception) {
            log.error(e.message, e)
            presenter.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR, e)
        }
    }

    @Transactional
    private fun executeWithoutHandleExceptions(id: String, presenter: PersonaPresenter) {
        val deleted = personaRepository.delete(id)
        presenter.sendResponse(deleted)
    }
}
