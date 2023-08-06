package com.makobrothers.mako.rrhh.persona.application.port.input

interface PersonaFindByCriteriaUseCase {

  fun execute(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter)

}