package com.makobrothers.rrhh.commons.application.port.output

import com.makobrothers.rrhh.persona.application.port.output.PersonaRepository

/**
 * Factoría de adaptadores.
 * *
 * *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
interface AdapterFactory {

    fun getPersonaRepository(): PersonaRepository

}