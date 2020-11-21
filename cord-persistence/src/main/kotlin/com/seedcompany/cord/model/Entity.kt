package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import java.time.ZonedDateTime
import java.util.*

open class Entity(
    @Id var id: String = UUID.randomUUID().toString(),
    var createdAt: ZonedDateTime = ZonedDateTime.now()
)