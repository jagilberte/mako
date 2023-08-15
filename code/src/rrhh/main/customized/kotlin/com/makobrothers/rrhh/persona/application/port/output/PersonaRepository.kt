package com.makobrothers.rrhh.persona.application.port.output

import com.makobrothers.shared.infrastructure.repository.Repository
import com.makobrothers.rrhh.persona.domain.entities.Persona
import com.makobrothers.rrhh.persona.domain.criteria.PersonaCriteria

/**
 * Interface de la clase de acceso a datos de la entidad Persona
 * extiende la interficie generada por el MDA. Aquí se extiende la
 * funcionalidad del repository si se requiere.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com)
 */

interface PersonaRepository : Repository<Persona, String, PersonaCriteria>