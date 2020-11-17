package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class User(
        val name: String,
        @Relationship(type="name", direction = Relationship.Direction.OUTGOING)
        var names: List<PropertyRelationship>
): BaseNode()
