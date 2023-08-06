package com.makobrothers.mako.rrhh.persona.infrastructure.input.rsocket

import com.makobrothers.ddd.commons.delivery.impl.RESTPresenterImpl
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona

class PersonaRSocketPresenter : RESTPresenterImpl<Persona>(), PersonaPresenter