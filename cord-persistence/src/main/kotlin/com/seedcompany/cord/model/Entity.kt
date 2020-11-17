package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Id
import java.time.ZonedDateTime
import java.util.*

open class Entity {
    @Id val id: String = UUID.randomUUID().toString()
    val createdAt: ZonedDateTime = ZonedDateTime.now()
}