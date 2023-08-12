package com.makobrothers.mako.rrhh.persona.application.port.input

import com.makobrothers.shared.commons.dto.criteria.BaseCriteria
import com.makobrothers.rrhh.persona.domain.criteria.PersonaCriteria
import com.makobrothers.rrhh.persona.domain.entities.*
import java.util.*

/**
 * Criteria para filtrar datos de la entidad Persona.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com)
 */
data class PersonaCriteriaRequestModel(var eqId: String? = null,
    var ilikeNom: String? = null,
    var ilikePrimerCognom: String? = null,
    var ilikeSegonCognom: String? = null,
    var ilikeNifNie: String? = null,
    var ilikeAdrecaCE: String? = null,
    var ilikeMobilPersonal: String? = null,
    var eqResponsableDirecte: String? = null,
    var geDataIncorporacio: Date? = null,
    var leDataIncorporacio: Date? = null,
    var eqDataIncorporacio: Date? = null,
    var geDataPrevistaFinalitzacio: Date? = null,
    var leDataPrevistaFinalitzacio: Date? = null,
    var eqDataPrevistaFinalitzacio: Date? = null,
    var geDataNaixement: Date? = null,
    var leDataNaixement: Date? = null,
    var eqDataNaixement: Date? = null,
    var eqEsResponsable: Boolean? = null,
    var eqAltaAGid: Boolean? = null,
    var geDataCreacio: Date? = null,
    var leDataCreacio: Date? = null,
    var eqDataCreacio: Date? = null,
    var geDataModificacio: Date? = null,
    var leDataModificacio: Date? = null,
    var eqDataModificacio: Date? = null,
    var geDataBaixa: Date? = null,
    var leDataBaixa: Date? = null,
    var eqDataBaixa: Date? = null,
    ) : BaseCriteria() {

    fun toPersonaCriteria(): PersonaCriteria {
        return PersonaCriteria(eqId,
            ilikeNom,
            ilikePrimerCognom,
            ilikeSegonCognom,
            ilikeNifNie,
            ilikeAdrecaCE,
            ilikeMobilPersonal,
            eqResponsableDirecte,
            geDataIncorporacio,
            leDataIncorporacio,
            eqDataIncorporacio,
            geDataPrevistaFinalitzacio,
            leDataPrevistaFinalitzacio,
            eqDataPrevistaFinalitzacio,
            geDataNaixement,
            leDataNaixement,
            eqDataNaixement,
            eqEsResponsable,
            eqAltaAGid,
            geDataCreacio,
            leDataCreacio,
            eqDataCreacio,
            geDataModificacio,
            leDataModificacio,
            eqDataModificacio,
            geDataBaixa,
            leDataBaixa,
            eqDataBaixa,
            )
    }
}