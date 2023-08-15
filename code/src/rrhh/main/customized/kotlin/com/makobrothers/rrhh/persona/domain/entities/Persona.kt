package com.makobrothers.rrhh.persona.domain.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.makobrothers.shared.commons.domain.entities.SelfValidating
import com.makobrothers.shared.commons.dto.model.SqlParametrizable
import java.util.Date
import jakarta.validation.constraints.*
import jakarta.validation.Validation

/**
 * Bean que define los datos de la entidad Persona. Se implementan los
 * getters y setters. Y funciones de comparación toString, toXML etc.
 * Tiene un surrogate key que es el atributo id y un campo para controlar
 * la concurrencia de manera optimista (atributo version).
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 *
 */
data class Persona(@get:NotNull override var id: String? = "0",
    @get:NotNull override var version: Int = 0,
    @get:NotNull var nom: String? = null,
    @get:NotNull var primerCognom: String? = null,
    var segonCognom: String? = null,
    var nifNie: String? = null,
    var alies: String? = null,
    var adrecaCE: String? = null,
    var mobilPersonal: String? = null,
    var responsableDirecte: Persona? = null,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") @get:NotNull var dataIncorporacio: Date? = null,
    var dataPrevistaFinalitzacio: Date? = null,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") @get:NotNull var dataNaixement: Date? = null,
    var dataBaixaEfectiva: Date? = null,
    var curriculumVitae: String? = null,
    var foto: String? = null,
    @get:NotNull var esResponsable: Boolean? = null,
    @get:NotNull var altaAGid: Boolean? = null,
    var emailGID: String? = null,
    var organitzacio: String? = null,
    var dependencia: String? = null,
    var indentificadorGID: String? = null,
    var gid6: String? = null,
    @get:NotNull var creatPer: String? = null,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") @get:NotNull var dataCreacio: Date? = null,
    var modificatPer: String? = null,
    var dataModificacio: Date? = null,
    var dataBaixa: Date? = null,
    ) : SelfValidating<Persona>(), SqlParametrizable<String> {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    override fun toMapSqlParameterSource(): MutableMap<String, Any?> {
        val map = LinkedHashMap<String, Any?>()
        map.put("ID_PERSONA", id)
        map.put("VERSION", Integer.valueOf(version))
        map.put("NOM", nom)
        map.put("PRIMER_COGNOM", primerCognom)
        map.put("SEGON_COGNOM", segonCognom)
        map.put("NIF_NIE", nifNie)
        map.put("ALIES", alies)
        map.put("ADRECA_CE", adrecaCE)
        map.put("MOBIL_PERSONAL", mobilPersonal)
        map.put("RESPONSABLE_DIRECTE", responsableDirecte)
        map.put("DATA_INCORPORACIO", dataIncorporacio)
        map.put("DATA_PREVISTA_FINALITZACIO", dataPrevistaFinalitzacio)
        map.put("DATA_NAIXEMENT", dataNaixement)
        map.put("DATA_BAIXA_EFECTIVA", dataBaixaEfectiva)
        map.put("CURRICULUM_VITAE", curriculumVitae)
        map.put("FOTO", foto)
        map.put("ES_RESPONSABLE", esResponsable)
        map.put("ALTA_A_GID", altaAGid)
        map.put("EMAIL_GID", emailGID)
        map.put("ORGANITZACIO_GID", organitzacio)
        map.put("DEPENDENCIA_GID", dependencia)
        map.put("IDENTIFICADOR_GID", indentificadorGID)
        map.put("GID6", gid6)
        map.put("CREAT_PER", creatPer)
        map.put("DATA_CREACIO", dataCreacio)
        map.put("MODIFICAT_PER", modificatPer)
        map.put("DATA_MODIFICACIO", dataModificacio)
        map.put("DATA_BAIXA", dataBaixa)
        return map
    }

}