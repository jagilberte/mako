package com.makobrothers.mako.rrhh.persona.application

import com.makobrothers.cleanarchitecture.commons.delivery.MockResponseEntity
import com.makobrothers.cleanarchitecture.commons.delivery.MockPresenter
import com.makobrothers.mako.UnitTest
import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.mock.*
import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.application.port.output.*
import com.makobrothers.mako.rrhh.persona.domain.entities.Persona
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaUpdateServiceSpec extends Specification {

    def 'Test update a null object persona to see the validation errors'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaRequestModel personaRequest = new PersonaRequestModel()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
        when:
            MockResponseEntity<Persona> response = executeServiceUpdate(adapterFactory, presenter, personaRequest)
        then:
            assert MockResponseEntity.NOT_ACCEPTABLE == response.getStatusCode()
    }

    def 'Test update an object persona with all fields well informed'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaRequestModel personaRequest = createPersonaRequestModel("TEST")
            AdapterFactory adapterFactory = getAdapterFactoryMock()
        when:
            MockResponseEntity<Persona> response = executeServiceUpdate(adapterFactory, presenter, personaRequest)
        then:
            assert MockResponseEntity.OK == response.getStatusCode()
    }

    def 'Test update generate a Exception'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaRequestModel personaRequest = createPersonaRequestModel("TEST")
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().update(_) >> { throw new Exception("Not found exception") }
        when:
            MockResponseEntity<Persona> response = executeServiceUpdate(adapterFactory, presenter, personaRequest)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    private MockResponseEntity<Persona> executeServiceUpdate(AdapterFactory adapterFactory, MockPresenter<Persona> presenter, PersonaRequestModel personaRequest) {
        PersonaUpdateService service = new PersonaUpdateService(adapterFactory)
        service.execute(personaRequest, presenter)
        return presenter.generateResponseWithValidations()
    }

    private AdapterFactory getAdapterFactoryMock() {
        AdapterFactory adapterFactory = Mock()
        PersonaRepository personaRepository = Mock()
        adapterFactory.getPersonaRepository() >> personaRepository
        return adapterFactory
    }

    private PersonaRequestModel createPersonaRequestModel(String cadenaTest) {
        return new PersonaRequestModel(
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
