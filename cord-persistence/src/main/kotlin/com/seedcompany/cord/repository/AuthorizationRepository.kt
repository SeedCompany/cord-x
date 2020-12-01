package com.seedcompany.cord.repository

import com.seedcompany.cord.model.GlobalSecurityGroup
import com.seedcompany.cord.model.Role
import com.seedcompany.cord.model.SecurityGroup
import com.seedcompany.cord.model.User
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface AuthorizationRepository : ReactiveNeo4jRepository<SecurityGroup, String> {
    fun findByRole(role: Role): Mono<SecurityGroup>
    fun findByGlobalRole(role: Role): Mono<GlobalSecurityGroup>
}
