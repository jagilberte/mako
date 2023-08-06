package com.makobrothers.mako.rrhh.persona.application.port.input

import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaRequestModel

interface PersonaInsertUseCase {

  fun execute(requestModel: PersonaRequestModel, presenter: PersonaPresenter)

}