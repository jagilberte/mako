package com.makobrothers.rrhh.persona.application.port.input

import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.application.port.input.PersonaCriteriaRequestModel

interface PersonaDeleteByCriteriaUseCase {

  fun execute(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter)

}