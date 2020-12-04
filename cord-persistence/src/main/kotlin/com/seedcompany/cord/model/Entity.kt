package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.support.UUIDStringGenerator
import java.time.ZonedDateTime
import java.util.*

open class Entity {
    @Id
    @GeneratedValue(UUIDStringGenerator::class)
    open lateinit var id: String
    var createdAt: ZonedDateTime = ZonedDateTime.now()
    var modifiedAt: ZonedDateTime = ZonedDateTime.now()
    var deletedAt: ZonedDateTime? = null
        set(value){
            if (value == null) return
            modifiedAt = value
            field = value
        }
}