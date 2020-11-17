package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
open class PropertyRelationship(
        var active: Boolean,
        @TargetNode
        val toNode: PropertyNode,
) : Entity()