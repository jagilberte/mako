package com.makobrothers.mako.rrhh.persona.application

import com.makobrothers.cleanarchitecture.commons.delivery.MockResponseEntity
import com.makobrothers.cleanarchitecture.commons.delivery.MockPresenter
import com.makobrothers.mako.UnitTest
import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.mock.*
import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.application.port.output.*
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaDeleteByCriteriaServiceSpec extends Specification {

    def 'Test deleteByCriteria with 0 rows deleted'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaCriteriaRequestModel criteriaRequestModel = new PersonaCriteriaRequestModel()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().deleteByCriteria(_) >> 0
        when:
            MockResponseEntity<Integer> response = executeServiceDeleteByCriteria(adapterFactory, presenter, criteriaRequestModel)
        then:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test deleteByCriteria with 2 rows deleted'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaCriteriaRequestModel criteriaRequestModel = new PersonaCriteriaRequestModel()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().deleteByCriteria(_) >> 2
        when:
            MockResponseEntity<Integer> response = executeServiceDeleteByCriteria(adapterFactory, presenter, criteriaRequestModel)
        then:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test deleteByCriteria generate a Exception'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaCriteriaRequestModel criteriaRequestModel = new PersonaCriteriaRequestModel()
            criteriaRequestModel.eqId = "1"
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().deleteByCriteria(_) >> { throw new Exception("Not found exception") }
        when:
            MockResponseEntity<Integer> response = executeServiceDeleteByCriteria(adapterFactory, presenter, criteriaRequestModel)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    private static MockResponseEntity<Integer> executeServiceDeleteByCriteria(AdapterFactory adapterFactory, PersonaMockPresenter presenter, PersonaCriteriaRequestModel criteria) {
        PersonaDeleteByCriteriaService service = new PersonaDeleteByCriteriaService(adapterFactory)
        service.execute(criteria, presenter)
        return presenter.generateResponseDelete()
    }

    private AdapterFactory getAdapterFactoryMock() {
        AdapterFactory adapterFactory = Mock()
        PersonaRepository personaRepository = Mock()
        adapterFactory.getPersonaRepository() >> personaRepository
        return adapterFactory
    }

}