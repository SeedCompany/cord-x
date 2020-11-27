package com.seedcompany.cord.repository

import com.seedcompany.cord.model.User
import org.reactivestreams.Publisher
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveNeo4jRepository<User, String> {
    fun findByEmail(email: String): Mono<User>
}
