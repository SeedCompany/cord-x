package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
open class PropertyRelationship() : Entity()

@RelationshipProperties
open class StringPropertyRelationship(
        @TargetNode var toNode: StringProp
) : PropertyRelationship()

@RelationshipProperties
open class BooleanPropertyRelationship(
        @TargetNode var toNode: BooleanProp
) : PropertyRelationship()

@RelationshipProperties
open class NumberPropertyRelationship(
        @TargetNode var toNode: NumberProp
) : PropertyRelationship()