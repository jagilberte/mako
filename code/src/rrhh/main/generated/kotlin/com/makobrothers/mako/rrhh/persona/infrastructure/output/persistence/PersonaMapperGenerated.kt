package com.makobrothers.rrhh.persona.infrastructure.output.persistence

import com.makobrothers.shared.commons.dto.mapper.MapperRes
import com.makobrothers.rrhh.persona.domain.entities.Persona
import org.springframework.jdbc.core.RowMapper
import java.sql.*

/**
 * Mapper que convierte un ResultSet a un Persona.
 * @author José-Alberto Gilberte León (jagilberte@gmail.com).
 */
open class PersonaMapperGenerated : RowMapper<Persona> {

    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Persona? {
        val mapperRes = MapperRes<Persona>()
        val tablePrefix = "T0"
        val persona = mapPersona(rs, tablePrefix)
        mapperRes.dto = persona
        return mapperRes.dto
    }

    private fun mapPersona(rs: ResultSet, tablePrefix: String): Persona {
        val persona = Persona()
        persona.id = rs.getString(tablePrefix + "ID_PERSONA")
        persona.version = rs.getInt(tablePrefix + "VERSION")
        persona.nom = rs.getString(tablePrefix + "NOM")
        persona.primerCognom = rs.getString(tablePrefix + "PRIMER_COGNOM")
        persona.segonCognom = rs.getString(tablePrefix + "SEGON_COGNOM")
        persona.nifNie = rs.getString(tablePrefix + "NIF_NIE")
        persona.alies = rs.getString(tablePrefix + "ALIES")
        persona.adrecaCE = rs.getString(tablePrefix + "ADRECA_CE")
        persona.mobilPersonal = rs.getString(tablePrefix + "MOBIL_PERSONAL")
        persona.responsableDirecte = createRelationResponsableDirecte(rs, tablePrefix)
        persona.dataIncorporacio = rs.getDate(tablePrefix + "DATA_INCORPORACIO")
        persona.dataPrevistaFinalitzacio = rs.getDate(tablePrefix + "DATA_PREVISTA_FINALITZACIO")
        persona.dataNaixement = rs.getDate(tablePrefix + "DATA_NAIXEMENT")
        persona.dataBaixaEfectiva = rs.getDate(tablePrefix + "DATA_BAIXA_EFECTIVA")
        persona.curriculumVitae = rs.getString(tablePrefix + "CURRICULUM_VITAE")
        persona.foto = rs.getString(tablePrefix + "FOTO")
        persona.esResponsable = rs.getBoolean(tablePrefix + "ES_RESPONSABLE")
        persona.altaAGid = rs.getBoolean(tablePrefix + "ALTA_A_GID")
        persona.emailGID = rs.getString(tablePrefix + "EMAIL_GID")
        persona.organitzacio = rs.getString(tablePrefix + "ORGANITZACIO_GID")
        persona.dependencia = rs.getString(tablePrefix + "DEPENDENCIA_GID")
        persona.indentificadorGID = rs.getString(tablePrefix + "IDENTIFICADOR_GID")
        persona.gid6 = rs.getString(tablePrefix + "GID6")
        persona.creatPer = rs.getString(tablePrefix + "CREAT_PER")
        persona.dataCreacio = rs.getDate(tablePrefix + "DATA_CREACIO")
        persona.modificatPer = rs.getString(tablePrefix + "MODIFICAT_PER")
        persona.dataModificacio = rs.getDate(tablePrefix + "DATA_MODIFICACIO")
        persona.dataBaixa = rs.getDate(tablePrefix + "DATA_BAIXA")
        return persona
    }

    private fun createRelationResponsableDirecte(rs: ResultSet, tablePrefix: String): Persona {
        var responsableDirecte = Persona(ID_EMPTY, VERSION_EMPTY)
        if (rs.getString(tablePrefix + "RESPONSABLE_DIRECTE") != null) {
            responsableDirecte = Persona(rs.getString(tablePrefix + "RESPONSABLE_DIRECTE"))
        }
        return responsableDirecte
    }

    companion object {
        private const val ID_EMPTY = "0"
        private const val VERSION_EMPTY = 0
    }

}