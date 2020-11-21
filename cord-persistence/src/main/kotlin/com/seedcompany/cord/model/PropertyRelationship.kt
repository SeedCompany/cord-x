package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
open class PropertyRelationship(
        var active: Boolean = true
) : Entity()

@RelationshipProperties
open class StringPropertyRelationship(
        active: Boolean,
        @TargetNode var toNode: StringProp
) : PropertyRelationship(active)

@RelationshipProperties
open class BooleanPropertyRelationship(
        active: Boolean,
        @TargetNode var toNode: BooleanProp
) : PropertyRelationship(active)

@RelationshipProperties
open class NumberPropertyRelationship(
        active: Boolean,
        @TargetNode var toNode: NumberProp
) : PropertyRelationship(active)