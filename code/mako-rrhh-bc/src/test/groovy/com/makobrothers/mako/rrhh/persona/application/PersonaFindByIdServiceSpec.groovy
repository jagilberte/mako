package com.makobrothers.mako.rrhh.persona.application

import com.makobrothers.cleanarchitecture.commons.delivery.MockResponseEntity
import com.makobrothers.cleanarchitecture.commons.delivery.MockPresenter
import com.makobrothers.mako.UnitTest
import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.mock.*
import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.application.port.output.*
import com.makobrothers.mako.rrhh.persona.domain.entities.*
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaFindByIdServiceSpec extends Specification {

    def 'Test findById with id not found'() {
        given:
            String id = "0"
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().findById(id) >>> new Persona()
        when:
            MockResponseEntity<Persona> response = executeServiceFindById(adapterFactory, presenter, id)
        then:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test findById with id found'() {
        given:
            String id = "1"
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().findById(id) >>> createPersona("TEST")
        when:
            MockResponseEntity<Persona> response = executeServiceFindById(adapterFactory, presenter, id)
        then:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test findById generate a Exception'() {
        given:
            String id = "1"
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().findById(_) >> { throw new Exception("Not found exception") }
        when:
            MockResponseEntity<Persona> response = executeServiceFindById(adapterFactory, presenter, id)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    private static MockResponseEntity<Persona> executeServiceFindById(AdapterFactory adapterFactory, MockPresenter<Persona> presenter, String id) {
        PersonaFindByIdService service = new PersonaFindByIdService(adapterFactory)
        service.execute(id, presenter)
        return presenter.generateResponse()
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