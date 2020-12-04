package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

@Node
class Token (
        override var id: String,
        @Relationship(type = "token", direction = Relationship.Direction.INCOMING)
        @JsonIgnore
        var user: User? = null,
): BaseNode()