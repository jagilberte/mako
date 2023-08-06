package com.makobrothers.mako.rrhh.persona.application

import com.makobrothers.cleanarchitecture.commons.delivery.MockResponseEntity
import com.makobrothers.cleanarchitecture.commons.delivery.MockPresenter
import com.makobrothers.mako.UnitTest
import com.makobrothers.cleanarchitecture.commons.dto.Page
import com.makobrothers.mako.commons.application.port.output.AdapterFactory
import com.makobrothers.mako.rrhh.persona.application.mock.*
import com.makobrothers.mako.rrhh.persona.application.port.input.*
import com.makobrothers.mako.rrhh.persona.application.port.output.*
import com.makobrothers.mako.rrhh.persona.domain.criteria.*
import com.makobrothers.mako.rrhh.persona.domain.entities.*
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(UnitTest.class)
class PersonaFindPageByCriteriaServiceSpec extends Specification {
    def emptyList = []

    def 'Test findByCriteriaPage with not found'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            PersonaCriteriaPagedRequestModel criteria = new PersonaCriteriaPagedRequestModel()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            Page personaPage = createPersonaPage()
        when:
            MockResponseEntity<Page<Persona>> response = executeServiceFindPageByCriteria(adapterFactory, presenter, criteria)
        then:
            1 * adapterFactory.getPersonaRepository().findPageByCriteria(_) >> personaPage
        and:
            assert (response.getStatusCode() == MockResponseEntity.OK)
    }

    def 'Test findByCriteriaPage found one persona'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            PersonaCriteriaPagedRequestModel criteria = new PersonaCriteriaPagedRequestModel()
            criteria.eqId = 1
            Page personaPage = createPersonaPageWithData()
        when:
            MockResponseEntity<Page<Persona>> response = executeServiceFindPageByCriteria(adapterFactory, presenter, criteria)
        then:
            1 * adapterFactory.getPersonaRepository().findPageByCriteria(_) >> personaPage
        and:
            assert (response.getStatusCode() == MockResponseEntity.OK)
            assert (response.getBody().getContent().get(0).getId() == "1")
    }

    def 'Test findByCriteriaPage generate a Exception'() {
        given:
            PersonaMockPresenter presenter = new PersonaMockPresenter()
            AdapterFactory adapterFactory = getAdapterFactoryMock()
            PersonaCriteriaPagedRequestModel criteria = new PersonaCriteriaPagedRequestModel()
            adapterFactory.getPersonaRepository().findPageByCriteria(_) >> { throw new Exception("Not found exception") }
        when:
            MockResponseEntity<Page<Persona>> response = executeServiceFindPageByCriteria(adapterFactory, presenter, criteria)
        then:
            assert (response.getStatusCode() == MockResponseEntity.INTERNAL_SERVER_ERROR)
    }

    Page createPersonaPage() {
        Page personaPage = new Page<Persona>()
        personaPage.setContent(emptyList)
        return personaPage
    }

    Page createPersonaPageWithData() {
        List<Persona> personaList = new ArrayList<Persona>()
        Persona persona = createPersona("TEST")
        personaList.add(persona)
        Page personaPage = new Page<Persona>()
        personaPage.setContent(personaList)
        return personaPage
    }

    private static MockResponseEntity<Page<Persona>> executeServiceFindPageByCriteria(AdapterFactory adapterFactory, PersonaPresenter presenter, PersonaCriteriaPagedRequestModel criteria) {
        PersonaFindPageByCriteriaService service = new PersonaFindPageByCriteriaService(adapterFactory)
        service.execute(criteria, presenter)
        return presenter.generateResponsePage()
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