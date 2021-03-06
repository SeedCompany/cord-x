package com.seedcompany.cord.repository

import com.seedcompany.cord.model.EmailToken
import com.seedcompany.cord.model.Token
import com.seedcompany.cord.model.User
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EmailTokenRepository : ReactiveNeo4jRepository<EmailToken, String>