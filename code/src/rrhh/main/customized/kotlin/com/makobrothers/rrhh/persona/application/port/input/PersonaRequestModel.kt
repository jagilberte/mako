package com.makobrothers.rrhh.persona.application.port.input

import com.makobrothers.rrhh.persona.domain.entities.Persona
import java.util.*

/**
 * Bean que define los datos de la entidad Persona. Se implementan los
 * getters y setters. Y funciones de comparación toString, toXML etc.
 * Tiene un surrogate key que es el atributo id y un campo para controlar
 * la concurrencia de manera optimista (atributo version).
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 *
 */
data class PersonaRequestModel(var id: String? = null,
    var version: Int = 0,
    var nom: String? = null,
    var primerCognom: String? = null,
    var segonCognom: String? = null,
    var nifNie: String? = null,
    var alies: String? = null,
    var adrecaCE: String? = null,
    var mobilPersonal: String? = null,
    var responsableDirecte: String? = null,
    var dataIncorporacio: Date? = null,
    var dataPrevistaFinalitzacio: Date? = null,
    var dataNaixement: Date? = null,
    var dataBaixaEfectiva: Date? = null,
    var curriculumVitae: String? = null,
    var foto: String? = null,
    var esResponsable: Boolean? = null,
    var altaAGid: Boolean? = null,
    var emailGID: String? = null,
    var organitzacio: String? = null,
    var dependencia: String? = null,
    var indentificadorGID: String? = null,
    var gid6: String? = null,
    var creatPer: String? = null,
    var dataCreacio: Date? = null,
    var modificatPer: String? = null,
    var dataModificacio: Date? = null,
    var dataBaixa: Date? = null,
    ) {

    fun toPersona(): Persona {
        return Persona(id,
            version,
            nom,
            primerCognom,
            segonCognom,
            nifNie,
            alies,
            adrecaCE,
            mobilPersonal,
            (if (this.responsableDirecte != null) Persona(id = this.responsableDirecte!!) else null),
            dataIncorporacio,
            dataPrevistaFinalitzacio,
            dataNaixement,
            dataBaixaEfectiva,
            curriculumVitae,
            foto,
            esResponsable,
            altaAGid,
            emailGID,
            organitzacio,
            dependencia,
            indentificadorGID,
            gid6,
            creatPer,
            dataCreacio,
            modificatPer,
            dataModificacio,
            dataBaixa,
            )

    }
}