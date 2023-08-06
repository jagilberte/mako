package com.makobrothers.mako.rrhh.persona.application.port.input

import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaCriteriaRequestModel

interface PersonaDeleteByCriteriaUseCase {

  fun execute(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter)

}