package com.makobrothers.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.shared.commons.delivery.impl.RESTPresenterImpl
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.domain.entities.Persona

class PersonaRSocketPresenter : RESTPresenterImpl<Persona>(), PersonaPresenter