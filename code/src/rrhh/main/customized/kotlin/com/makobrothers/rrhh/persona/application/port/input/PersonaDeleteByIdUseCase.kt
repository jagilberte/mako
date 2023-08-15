package com.makobrothers.rrhh.persona.application.port.input

import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter

interface PersonaDeleteByIdUseCase {
  
  fun execute(id: String, presenter: PersonaPresenter)

}