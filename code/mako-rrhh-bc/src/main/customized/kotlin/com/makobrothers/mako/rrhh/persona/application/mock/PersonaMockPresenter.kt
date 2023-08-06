package com.makobrothers.mako.rrhh.persona.application.mock

import com.makobrothers.ddd.commons.delivery.impl.MockPresenterImpl
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona

class PersonaMockPresenter : MockPresenterImpl<Persona>(), PersonaPresenter