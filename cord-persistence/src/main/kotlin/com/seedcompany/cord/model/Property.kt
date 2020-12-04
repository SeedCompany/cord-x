package com.seedcompany.cord.model

import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.Transient
import org.springframework.data.neo4j.core.schema.DynamicLabels
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

// TODO: figure out how to use generics with the mapper
// naive impl results in a primary label error

class StringProp(
        var value: String? = null,
        var deletedValue: String? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

class BooleanProp(
        var value: Boolean? = null,
        var deletedValue: Boolean? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

class NumberProp(
        var value: Long? = null,
        var deletedValue: Long? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

class RoleProp(
        var value: Role? = null,
        var deletedValue: Role? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

class EmailProp(
        @Index(unique = true)
        var value: String? = null,
        var deletedValue: String? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

class UserStatusProp(
        var value: UserStatus? = null,
        var deletedValue: UserStatus? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

class OrgNameProp(
        @Index(unique = true)
        var value: String? = null,
        var deletedValue: String? = null,
        @DynamicLabels
        var labels: Collection<String>,
) : Entity()

//@Transient
// class PropNode<T> (
//         var value: T? = null,
//         var deletedValue: T? = null,
//        @DynamicLabels
//         var labels: Collection<String>,
//): Entity()
//
//@Transient
//class UniqPropNode<T> (
//        @Index(unique = true)
//        var value: T? = null,
//        var deletedValue: T? = null,
//        @DynamicLabels
//        var labels: Collection<String>,
//): Entity()

enum class PropName(val str: String) {
    UserAbout("about"),
    UserDisplayFirstName("displayFirstName"),
    UserDisplayLastName("displayLastName"),
    UserEducation("education"),
    UserEmail("email"),
    UserPassword("password"),
    UserPhone("phone"),
    UserRealFirstName("realFirstName"),
    UserRealLastName("realLastName"),
    UserRoles("roles"),
    UserStatus("status"),
    UserTimezone("timezone"),
    UserTitle("title"),
}