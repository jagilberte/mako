package com.makobrothers.rrhh.persona.application.mock

import com.makobrothers.shared.delivery.impl.MockPresenterImpl
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.domain.entities.Persona

class PersonaMockPresenter : MockPresenterImpl<Persona>(), PersonaPresenter