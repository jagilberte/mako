package com.makobrothers.mako.rrhh.persona.application.port.input

import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaRequestModel

interface PersonaUpdateUseCase {

  fun execute(requestModel: PersonaRequestModel, presenter: PersonaPresenter)
  
}