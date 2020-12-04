package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

@Node
class EmailToken (
        @Id
        var id: String,
        var email: String,
) {
    val createdAt: ZonedDateTime = ZonedDateTime.now()
}