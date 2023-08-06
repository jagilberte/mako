package com.makobrothers.mako.rrhh.persona.infrastructure.input.rest

import com.makobrothers.ddd.commons.delivery.impl.RESTPresenterImpl
import com.makobrothers.mako.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona

class PersonaRestPresenter : RESTPresenterImpl<Persona>(), PersonaPresenter