package com.makobrothers.rrhh.persona.application

import com.makobrothers.shared.commons.delivery.MockResponseEntity
import com.makobrothers.shared.commons.delivery.MockPresenter
import com.makobrothers.rrhh.UnitTest
import com.makobrothers.rrhh.commons.application.port.output.AdapterFactory
import com.makobrothers.rrhh.persona.application.mock.*
import com.makobrothers.rrhh.persona.application.port.input.*
import com.makobrothers.rrhh.persona.application.port.output.*
import com.makobrothers.rrhh.persona.domain.entities.*
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaInsertServiceSpec extends Specification {

    def 'Test insert a null object persona to see the validation errors'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaRequestModel personaRequest = new PersonaRequestModel()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
        when:
            MockResponseEntity<Persona> response = executeServiceInsert(adapterFactory, presenter, personaRequest)
        then:
            assert MockResponseEntity.NOT_ACCEPTABLE == response.getStatusCode()
    }

    def 'Test insert an object persona with all fields well informed'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaRequestModel personaRequest = createPersonaRequestModel("TEST")
            AdapterFactory adapterFactory = getAdapterFactoryMock()
        when:
            MockResponseEntity<Persona> response = executeServiceInsert(adapterFactory, presenter, personaRequest)
        then:
            assert MockResponseEntity.OK == response.getStatusCode()
    }

    def 'Test insert generate a Exception'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaRequestModel personaRequest = createPersonaRequestModel("TEST")
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().insert(_) >> { throw new Exception("Duplicate exception") }
        when:
            MockResponseEntity<Persona> response = executeServiceInsert(adapterFactory, presenter, personaRequest)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    private MockResponseEntity<Persona> executeServiceInsert(AdapterFactory adapterFactory, MockPresenter<Persona> presenter, PersonaRequestModel personaRequest) {
        PersonaInsertService service = new PersonaInsertService(adapterFactory)
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