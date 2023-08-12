package com.makobrothers.rrhh.persona.application

import com.makobrothers.shared.commons.domain.boundary.MessageCode

import com.makobrothers.rrhh.commons.application.port.output.AdapterFactory
import com.makobrothers.rrhh.persona.application.port.input.PersonaDeleteByCriteriaUseCase
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.application.port.input.PersonaCriteriaRequestModel
import com.makobrothers.rrhh.persona.domain.criteria.PersonaCriteria

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("personaDeleteByCriteriaService")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER_MOD')")
class PersonaDeleteByCriteriaService(private val adapterFactory: AdapterFactory) : PersonaDeleteByCriteriaUseCase {
        
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

    @Transactional
    private fun executeWithoutHandleExceptions(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter) {
        val criteria = requestModel.toPersonaCriteria()
        if (hasValuesInformed(criteria)) {
            val deleted = personaRepository.deleteByCriteria(criteria)
            presenter.sendResponse(deleted)
        }
    }

    private fun hasValuesInformed(criteria: PersonaCriteria): Boolean {
        var hasValues = false
        if (criteria != PersonaCriteria()) {
            hasValues = true
        }
        return hasValues
    }
}