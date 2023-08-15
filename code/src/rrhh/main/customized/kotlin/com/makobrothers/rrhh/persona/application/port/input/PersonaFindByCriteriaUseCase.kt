package com.makobrothers.rrhh.persona.application.port.input

interface PersonaFindByCriteriaUseCase {

  fun execute(requestModel: PersonaCriteriaRequestModel, presenter: PersonaPresenter)

}