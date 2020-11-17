package com.seedcompany.cord.repository

import com.seedcompany.cord.model.User
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ReactiveNeo4jRepository<User, String>
