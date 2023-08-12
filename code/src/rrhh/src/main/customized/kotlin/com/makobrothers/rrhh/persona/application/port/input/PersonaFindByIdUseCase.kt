package com.makobrothers.rrhh.persona.application.port.input

import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter

interface PersonaFindByIdUseCase {

  fun execute(id: String, presenter: PersonaPresenter)

}