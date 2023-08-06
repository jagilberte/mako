package com.makobrothers.mako.rrhh.persona.application

import com.makobrothers.cleanarchitecture.commons.delivery.MockResponseEntity
import com.makobrothers.cleanarchitecture.commons.delivery.MockPresenter
import com.makobrothers.mako.UnitTest
import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.mock.*
import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.application.port.output.*
import com.makobrothers.mako.rrhh.persona.domain.criteria.*
import com.makobrothers.mako.rrhh.persona.domain.entities.*
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaFindByCriteriaServiceSpec extends Specification {
    def emptyList = []

    def 'Test findByCriteria with not found'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaCriteriaRequestModel criteria = new PersonaCriteriaRequestModel()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
        when:
            MockResponseEntity<List<Persona>> response = executeServiceFindByCriteria(adapterFactory, presenter, criteria)
        then:
            1 * adapterFactory.getPersonaRepository().findByCriteria(_) >> emptyList
        and:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test findByCriteria found one persona'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            PersonaCriteriaRequestModel criteria = new PersonaCriteriaRequestModel()
            List<Persona> list = new ArrayList<Persona>()
            Persona persona = createPersona("CADENA_TEST")
            list.add(persona)
        when:
            MockResponseEntity<List<Persona>> response = executeServiceFindByCriteria(adapterFactory, presenter, criteria)
        then:
            1 * adapterFactory.getPersonaRepository().findByCriteria(_) >> list
        and:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test findByCriteria generate a Exception'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            PersonaCriteriaRequestModel criteria = new PersonaCriteriaRequestModel()
            adapterFactory.getPersonaRepository().findByCriteria(_) >> { throw new Exception("Not found exception") }
        when:
            MockResponseEntity<List<Persona>> response = executeServiceFindByCriteria(adapterFactory, presenter, criteria)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    private static MockResponseEntity<List<Persona>> executeServiceFindByCriteria(AdapterFactory adapterFactory, MockPresenter<Persona> presenter, PersonaCriteriaRequestModel criteria) {
        PersonaFindByCriteriaService service = new PersonaFindByCriteriaService(adapterFactory)
        service.execute(criteria, presenter)
        return presenter.generateResponseList()
    }

    private AdapterFactory getAdapterFactoryMock() {
        AdapterFactory adapterFactory = Mock()
        PersonaRepository personaRepository = Mock()
        adapterFactory.getPersonaRepository() >> personaRepository
        return adapterFactory
    }

    private Persona createPersona(String cadenaTest) {
        return new Persona(
            "1"
            ,0
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,null
            ,new Date()
            ,new Date()
            ,new Date()
            ,new Date()
            ,cadenaTest
            ,cadenaTest
            ,false
            ,false
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,cadenaTest
            ,new Date()
            ,cadenaTest
            ,new Date()
            ,new Date()
            )   
    }
}