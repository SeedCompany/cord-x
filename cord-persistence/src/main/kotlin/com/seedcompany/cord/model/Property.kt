package com.seedcompany.cord.model

import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.DynamicLabels
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@Node(labels = ["PropertyNode", "Property"])
open class PropertyNode () : Entity() {
    @DynamicLabels
    var labels: Collection<String> = listOf("Property")
}

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

@Node(labels = ["Role", "Property"])
class RoleProp(
        var value: Role? = null,
) : PropertyNode()

@Node(labels = ["Email", "Property"])
class EmailProp(
        @Index(unique = true)
        var value: String? = null,
        var deletedValue: String? = null,
) : PropertyNode()

@Node(labels = ["OrgName", "Property"])
class OrgNameProp(
        @Index(unique = true)
        var value: String? = null,
        var deletedValue: String? = null,
) : PropertyNode()

class TProp<T> (
        @DynamicLabels
        var labels: Collection<String> = listOf("Property"),
        var value: T? = null,
        var deletedValue: T? = null,
): Entity()

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