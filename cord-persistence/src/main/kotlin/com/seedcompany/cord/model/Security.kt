package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.CompositeProperty
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node(labels = ["SecurityGroupNode", "SecurityGroup"])
open class SecurityGroup(
        open var role: Role,
        open var powers: List<Power> = listOf(),
        @CompositeProperty
        open var grants: Map<PropName, Perm> = mapOf(),
        @Relationship(type = "member")
        @JsonIgnore
        var members: MutableList<User>,
) : BaseNode() {
}

interface GlobalSecurityGroupActiveReadOnly{
    fun getRole()
    fun getGrants()
}

@Node(labels = ["GlobalSecurityGroup", "SecurityGroup"])
class GlobalSecurityGroup(
        role: Role,
        powers: List<Power>,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
) : SecurityGroup(
        role = role,
        powers = powers,
        grants = grants,
        members = members,
) {
        @Index(unique = true)
        var globalRole: Role = role
}

@Node(labels = ["PublicSecurityGroup", "SecurityGroup"])
class PublicSecurityGroup(
        role: Role,
        powers: List<Power>,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
) : SecurityGroup(role, powers, grants, members)

@Node(labels = ["OrgSecurityGroup", "SecurityGroup"])
class OrgSecurityGroup(
        role: Role,
        powers: List<Power>,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
) : SecurityGroup(role, powers, grants, members)

interface ProjectSecurityGroupActiveReadOnly {
    fun getRole()
    fun getGrants()
    fun getProject()
}

@Node(labels = ["ProjectSecurityGroup", "SecurityGroup"])
class ProjectSecurityGroup(
        role: Role,
        powers: List<Power>,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
        var project: String?,
) : SecurityGroup(role, powers, grants, members) {
    @Relationship(type = "project")
    @JsonIgnore
    var _project: StringProp? = null// TODO: update when project model is created
}