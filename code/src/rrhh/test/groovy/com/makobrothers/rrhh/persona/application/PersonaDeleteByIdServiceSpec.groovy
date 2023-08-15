package com.makobrothers.rrhh.persona.application

import com.makobrothers.shared.commons.delivery.MockResponseEntity
import com.makobrothers.shared.commons.delivery.MockPresenter
import com.makobrothers.rrhh.UnitTest
import com.makobrothers.rrhh.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.mock.*
import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.application.port.output.*
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaDeleteByIdServiceSpec extends Specification {

    def 'Test deleteById with 0 rows deleted'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            String id = "0"
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().delete(_) >> 0
        when:
            MockResponseEntity<Integer> response = executeServiceDeleteById(adapterFactory, presenter, id)
        then:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test deleteById with 1 rows deleted'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            String id = "1"
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().delete(_) >> 1
        when:
            MockResponseEntity<Integer> response = executeServiceDeleteById(adapterFactory, presenter, id)
        then:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }


    def 'Test deleteById generate a Exception'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            String id = "178"
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            adapterFactory.getPersonaRepository().delete(_) >> { throw new Exception("Not found exception") }
        when:
            MockResponseEntity<Integer> response = executeServiceDeleteById(adapterFactory, presenter, id)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    private static MockResponseEntity<Integer> executeServiceDeleteById(AdapterFactory adapterFactory, MockPresenter presenter, String id) {
        PersonaDeleteByIdService service = new PersonaDeleteByIdService(adapterFactory)
        service.execute(id, presenter)
        return presenter.generateResponseDelete()
    }

    private AdapterFactory getAdapterFactoryMock() {
        AdapterFactory adapterFactory = Mock()
        PersonaRepository personaRepository = Mock()
        adapterFactory.getPersonaRepository() >> personaRepository
        return adapterFactory
    }

}