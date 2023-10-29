package com.makobrothers.apps.rrhh.backend.controller.persona.rest

import com.makobrothers.shared.delivery.impl.RESTPresenterImpl
import com.makobrothers.rrhh.persona.application.port.input.PersonaPresenter
import com.makobrothers.rrhh.persona.domain.entities.Persona

class PersonaRestPresenter : RESTPresenterImpl<Persona>(), PersonaPresenter