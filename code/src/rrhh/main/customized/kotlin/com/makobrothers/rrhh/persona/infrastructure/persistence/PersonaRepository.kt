package com.makobrothers.rrhh.persona.infrastructure.persistence

import com.makobrothers.shared.infrastructure.repository.impl.RepositoryJdbc
import com.makobrothers.rrhh.persona.application.port.output.PersonaRepository
import com.makobrothers.rrhh.persona.domain.entities.Persona
import com.makobrothers.rrhh.persona.domain.criteria.PersonaCriteria
import com.zaxxer.hikari.HikariDataSource
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import javax.sql.DataSource

/**
 * Implementación de la clase de acceso a datos de la entidad Persona
 * extiende la interficie generada por el MDA. Aquí se extiende la
 * funcionalidad del Repository si se requiere.
 *
 * @author José-Alberto Gilberte León (jagilberte@gmail.com)
 */
@Repository("personaRepository")
class PersonaRepository : RepositoryJdbc<Persona, String, PersonaMapper, PersonaCriteria, PersonaQuery>(), PersonaRepository {
    @Autowired
    val dataSourceRepository : DataSource = HikariDataSource()

    @PostConstruct
    private fun initialize() {
        this.dataSource = dataSourceRepository
        super.namedParameterJdbcOperations = NamedParameterJdbcTemplate(this.jdbcTemplate!!)
    }
}