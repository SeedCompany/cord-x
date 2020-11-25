package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.support.UUIDStringGenerator
import java.time.ZonedDateTime
import java.util.*

open class Entity {
    @Id
    @GeneratedValue(UUIDStringGenerator::class)
    lateinit var id: String
    val createdAt: ZonedDateTime = ZonedDateTime.now()
}