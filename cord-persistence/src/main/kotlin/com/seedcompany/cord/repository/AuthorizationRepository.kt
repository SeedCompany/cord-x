package com.seedcompany.cord.repository

import com.seedcompany.cord.model.SecurityGroup
import com.seedcompany.cord.model.User
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorizationRepository : ReactiveNeo4jRepository<SecurityGroup, String>
