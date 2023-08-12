package com.makobrothers.rrhh.persona.domain.criteria

import com.makobrothers.shared.commons.dto.criteria.BaseCriteria
import com.makobrothers.shared.commons.domain.utils.ParametersUtils

import java.util.*

/**
 * Criteria para filtrar datos de la entidad Persona.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com)
 */
data class PersonaCriteria(var eqId: String? = null,
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
    override var pageSize: Int = 10,
    override var actualPage: Int = 1) : BaseCriteria() {

    init {
        BaseCriteria()
    }

    /**
     * Lista de parámetros para filtrar en la cláusula where SQL.
     *
     * @return Retorna un objeto MapSqlParameterSource para filtrar los elementos
     * de la consulta en función de los atributos del criteria que no son
     * null.
     */
    override val mapSQLParameterSource: MutableMap<String, Any>
        get() {
            val parameters = LinkedHashMap<String, Any>()
            ParametersUtils.addStringParameter(parameters, "EQ_ID_PERSONA", eqId)
            ParametersUtils.addILikeParameter(parameters, "ILIKE_NOM", ilikeNom)
            ParametersUtils.addILikeParameter(parameters, "ILIKE_PRIMER_COGNOM", ilikePrimerCognom)
            ParametersUtils.addILikeParameter(parameters, "ILIKE_SEGON_COGNOM", ilikeSegonCognom)
            ParametersUtils.addILikeParameter(parameters, "ILIKE_NIF_NIE", ilikeNifNie)
            ParametersUtils.addILikeParameter(parameters, "ILIKE_ADRECA_CE", ilikeAdrecaCE)
            ParametersUtils.addILikeParameter(parameters, "ILIKE_MOBIL_PERSONAL", ilikeMobilPersonal)
            ParametersUtils.addStringParameter(parameters, "EQ_RESPONSABLE_DIRECTE", eqResponsableDirecte)
            ParametersUtils.addDateParameter(parameters, "GE_DATA_INCORPORACIO", geDataIncorporacio)
            ParametersUtils.addDateParameter(parameters, "LE_DATA_INCORPORACIO", leDataIncorporacio)
            ParametersUtils.addDateParameter(parameters, "EQ_DATA_INCORPORACIO", eqDataIncorporacio)
            ParametersUtils.addDateParameter(parameters, "GE_DATA_PREVISTA_FINALITZACIO", geDataPrevistaFinalitzacio)
            ParametersUtils.addDateParameter(parameters, "LE_DATA_PREVISTA_FINALITZACIO", leDataPrevistaFinalitzacio)
            ParametersUtils.addDateParameter(parameters, "EQ_DATA_PREVISTA_FINALITZACIO", eqDataPrevistaFinalitzacio)
            ParametersUtils.addDateParameter(parameters, "GE_DATA_NAIXEMENT", geDataNaixement)
            ParametersUtils.addDateParameter(parameters, "LE_DATA_NAIXEMENT", leDataNaixement)
            ParametersUtils.addDateParameter(parameters, "EQ_DATA_NAIXEMENT", eqDataNaixement)
            ParametersUtils.addBooleanParameter(parameters, "EQ_ES_RESPONSABLE", eqEsResponsable)
            ParametersUtils.addBooleanParameter(parameters, "EQ_ALTA_A_GID", eqAltaAGid)
            ParametersUtils.addDateParameter(parameters, "GE_DATA_CREACIO", geDataCreacio)
            ParametersUtils.addDateParameter(parameters, "LE_DATA_CREACIO", leDataCreacio)
            ParametersUtils.addDateParameter(parameters, "EQ_DATA_CREACIO", eqDataCreacio)
            ParametersUtils.addDateParameter(parameters, "GE_DATA_MODIFICACIO", geDataModificacio)
            ParametersUtils.addDateParameter(parameters, "LE_DATA_MODIFICACIO", leDataModificacio)
            ParametersUtils.addDateParameter(parameters, "EQ_DATA_MODIFICACIO", eqDataModificacio)
            ParametersUtils.addDateParameter(parameters, "GE_DATA_BAIXA", geDataBaixa)
            ParametersUtils.addDateParameter(parameters, "LE_DATA_BAIXA", leDataBaixa)
            ParametersUtils.addDateParameter(parameters, "EQ_DATA_BAIXA", eqDataBaixa)
            return parameters
        }
}