package com.seedcompany.cord.model

import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

enum class UserStatus {
    Active,
    Disabled,
}

@Node(labels = ["UserStatus", "Property"])
class UserStatusProp(
        var value: UserStatus? = null,
) : PropertyNode()

@RelationshipProperties
class UserStatusPropertyRelationship(
        @TargetNode var toNode: UserStatusProp
) : PropertyRelationship()