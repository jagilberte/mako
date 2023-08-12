package com.makobrothers.rrhh.persona.infrastructure.input.rest

import com.makobrothers.shared.commons.delivery.impl.RESTPresenterImpl
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.domain.entities.Persona

class PersonaRestPresenter : RESTPresenterImpl<Persona>(), PersonaPresenter