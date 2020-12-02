package com.seedcompany.cord.repository

import com.seedcompany.cord.model.Organization
import com.seedcompany.cord.model.OrganizationActiveReadOnly
import com.seedcompany.cord.model.User
import com.seedcompany.cord.model.UserActiveReadOnly
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface OrganizationRepository : ReactiveNeo4jRepository<Organization, String> {
    fun findByName(name: String): Mono<Organization>

    @Query("""
        MATCH (n:Organization {id: ${'$'}id }) 
        SET n:DeletedOrganization, 
            n.deletedAt = LocalDateTime(), 
            n.modifiedAt = LocalDateTime()
        REMOVE n:Organization
    """)
    override fun deleteById(@Param("id") id: String): Mono<Void>
}

@Repository
interface OrganizationActiveReadOnlyRepository : ReactiveNeo4jRepository<Organization, String> {
    fun findByName(name: String): Mono<OrganizationActiveReadOnly>

}