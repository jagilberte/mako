package com.makobrothers.rrhh.persona.application.port.input

import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.application.port.input.PersonaCriteriaPagedRequestModel

interface PersonaFindPageByCriteriaUseCase {

  fun execute(requestModel: PersonaCriteriaPagedRequestModel, presenter: PersonaPresenter)

}