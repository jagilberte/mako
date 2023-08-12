package com.makobrothers.rrhh.persona.application

import com.makobrothers.ddd.commons.domain.boundary.MessageCode

import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaFindByIdUseCase
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("personaFindByIdService")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER_CONS') || hasAuthority('USER_MOD')")
class PersonaFindByIdService(private val adapterFactory: AdapterFactory) : PersonaFindByIdUseCase {
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

    @Transactional(readOnly=true)
    private fun executeWithoutHandleExceptions(id: String, presenter: PersonaPresenter) {
        val persona = personaRepository.findById(id)
        if (persona != null) {
            presenter.sendResponse(persona)
        }
    }

}