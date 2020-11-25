package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@Node(labels = ["PropertyNode", "Property"])
open class PropertyNode () : Entity()

@Node(labels = ["StringProp", "Property"])
open class StringProp (
        open var value: String?
) : PropertyNode()

@Node(labels = ["BooleanProp", "Property"])
open class BooleanProp (
        var value: Boolean?
) : PropertyNode()

@Node(labels = ["NumberProp", "Property"])
open class NumberProp (
        var value: Long?
) : PropertyNode()

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