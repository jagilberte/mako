package com.makobrothers.mako.rrhh.persona.application

import com.makobrothers.ddd.commons.domain.boundary.MessageCode
import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaFindByCriteriaUseCase
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaCriteriaRequestModel

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("personaFindByCriteriaService")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER_CONS') || hasAuthority('USER_MOD')")
class PersonaFindByCriteriaService(private val adapterFactory: AdapterFactory) : PersonaFindByCriteriaUseCase {
    private final val log = LoggerFactory.getLogger(javaClass)
    private final val personaRepository = adapterFactory.getPersonaRepository()

    override fun execute(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter) {
        try {
            executeWithoutHandleExceptions(requestModel, presenter)
        } catch (e: Exception) {
            log.error(e.message, e)
            presenter.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR, e)
        }
    }

    @Transactional(readOnly=true)
    private fun executeWithoutHandleExceptions(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter) {
        val list = personaRepository.findByCriteria(requestModel.toPersonaCriteria())
        if (list.isNotEmpty()) {
            presenter.sendResponse(list)
        }
    }
}