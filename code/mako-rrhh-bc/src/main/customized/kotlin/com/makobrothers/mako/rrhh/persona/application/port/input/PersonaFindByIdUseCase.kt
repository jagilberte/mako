package com.makobrothers.mako.rrhh.persona.application.port.input

import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter

interface PersonaFindByIdUseCase {

  fun execute(id: String, presenter: PersonaPresenter)

}