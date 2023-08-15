package com.makobrothers.rrhh.persona.application

import com.makobrothers.shared.commons.domain.boundary.MessageCode

import com.makobrothers.rrhh.commons.application.port.output.AdapterFactory
import com.makobrothers.rrhh.persona.application.port.input.PersonaFindPageByCriteriaUseCase
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.application.port.input.PersonaCriteriaPagedRequestModel

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("personaFindPageByCriteriaService")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER_CONS') || hasAuthority('USER_MOD')")
class PersonaFindPageByCriteriaService(private val adapterFactory: AdapterFactory) : PersonaFindPageByCriteriaUseCase {
    private final val log = LoggerFactory.getLogger(javaClass)
    private final val personaRepository = adapterFactory.getPersonaRepository()

    override fun execute(requestModel: PersonaCriteriaPagedRequestModel, presenter: PersonaPresenter) {
        try {
            executeWithoutHandleExceptions(requestModel, presenter)
        } catch (e: Exception) {
            log.error(e.message, e)
            presenter.sendServerErrorMessage(MessageCode.INTERNAL_SERVER_ERROR, e)
        }
    }

    @Transactional(readOnly=true)
    private fun executeWithoutHandleExceptions(requestModel: PersonaCriteriaPagedRequestModel, presenter: PersonaPresenter) {
        val page = personaRepository.findPageByCriteria(requestModel.toPersonaCriteria())
        presenter.sendResponse(page)
    }

}