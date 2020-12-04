package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

@Node
class Token (
        @Id var id: String,
        var createdAt: ZonedDateTime = ZonedDateTime.now(),

        @Relationship(type = "user", direction = Relationship.Direction.OUTGOING)
        @JsonIgnore
        var user: User? = null,
)