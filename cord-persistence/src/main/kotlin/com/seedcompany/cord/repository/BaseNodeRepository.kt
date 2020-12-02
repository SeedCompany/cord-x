package com.seedcompany.cord.repository

import com.seedcompany.cord.model.BaseNode
import com.seedcompany.cord.model.User
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

//@Repository
//interface BaseNodeRepository<T> : ReactiveNeo4jRepository<T, String> {

//    @Query("MATCH (n:BaseNode {id: {id}) SET n.deletedId = {id}, n.id = NULL")
//    override fun deleteById(@Param("id") id: String): Mono<Void>
//}
