package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

@Node
class Token (
        @Id
        var id: String,
        @Relationship(type = "token", direction = Relationship.Direction.INCOMING)
        @JsonIgnore
        var user: User? = null,
) {
        val createdAt: ZonedDateTime = ZonedDateTime.now()
}