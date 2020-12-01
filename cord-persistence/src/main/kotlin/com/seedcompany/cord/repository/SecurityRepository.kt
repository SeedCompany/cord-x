package com.seedcompany.cord.repository

import com.seedcompany.cord.model.*
import com.seedcompany.cord.model.GlobalSecurityGroup
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface GlobalSecurityGroupRepository : ReactiveNeo4jRepository<GlobalSecurityGroup, String> {
    fun findByGlobalRole(role: Role): Mono<GlobalSecurityGroup>
}

@Repository
interface GlobalSecurityGroupActiveReadOnlyRepository : ReactiveNeo4jRepository<GlobalSecurityGroup, String> {
    fun findByGlobalRole(role: Role): Mono<GlobalSecurityGroupActiveReadOnly>
}

@Repository
interface ProjectSecurityGroupRepository : ReactiveNeo4jRepository<ProjectSecurityGroup, String> {
    fun findByRole(role: Role): Mono<ProjectSecurityGroup>
}

@Repository
interface ProjectSecurityGroupActiveReadOnlyRepository : ReactiveNeo4jRepository<ProjectSecurityGroup, String> {
    fun findByRole(role: Role): Mono<ProjectSecurityGroupActiveReadOnly>
}


