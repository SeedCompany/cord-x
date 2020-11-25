package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode
import org.neo4j.ogm.annotation.Index

@Node("Email")
class EmailProp(
        @Index(unique = true)
        var value: String? = null,
) : PropertyNode()

@RelationshipProperties
class EmailPropertyRelationship(
        @TargetNode var toNode: EmailProp
) : PropertyRelationship()