package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.CompositeProperty
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

open class SecurityGroup(
        open var role: Role,
        @CompositeProperty
        open var grants: Map<PropName, Perm>,
) : BaseNode() {
    @Relationship(type = "member")
    @JsonIgnore
    var members: MutableList<User> = mutableListOf()
}

@Node(labels = ["GlobalSecurityGroup", "SecurityGroup"])
class GlobalSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
) : SecurityGroup(
        role = role,
        grants = grants) {
        @Index(unique = true)
        var globalRole: Role = role
}

@Node(labels = ["PublicSecurityGroup", "SecurityGroup"])
class PublicSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
) : SecurityGroup(role, grants)

@Node(labels = ["OrgSecurityGroup", "SecurityGroup"])
class OrgSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
) : SecurityGroup(role, grants)

@Node(labels = ["ProjectSecurityGroup", "SecurityGroup"])
class ProjectSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
        var project: StringProp, // TODO: change to project node
) : SecurityGroup(role, grants)