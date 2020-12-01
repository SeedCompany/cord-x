package com.seedcompany.cord.model

import org.neo4j.ogm.annotation.Index
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

@Node(labels = ["Email", "Property"])
class EmailProp(
        @Index(unique = true)
        var value: String? = null,
) : PropertyNode()

@Node(labels = ["Role", "Property"])
class RoleProp(
        var value: Role? = null,
) : PropertyNode()

enum class PropName {
    UserAbout,
    UserDisplayFirstName,
    UserDisplayLastName,
    UserEmail,
    UserPhone,
    UserRealFirstName,
    UserRealLastName,
    UserRoles,
    UserStatus,
    UserTimezone,
    UserTitle,
}