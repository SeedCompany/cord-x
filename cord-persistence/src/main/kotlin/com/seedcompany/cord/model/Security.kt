package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.CompositeProperty
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node(labels = ["SecurityGroupNode", "SecurityGroup"])
open class SecurityGroup(
        open var role: Role,
        @CompositeProperty
        open var grants: Map<PropName, Perm>,
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
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
) : SecurityGroup(
        role = role,
        grants = grants,
        members = members,
) {
        @Index(unique = true)
        var globalRole: Role = role
}

@Node(labels = ["PublicSecurityGroup", "SecurityGroup"])
class PublicSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
) : SecurityGroup(role, grants, members)

@Node(labels = ["OrgSecurityGroup", "SecurityGroup"])
class OrgSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
) : SecurityGroup(role, grants, members)

interface ProjectSecurityGroupActiveReadOnly {
    fun getRole()
    fun getGrants()
    fun getProject()
}

@Node(labels = ["ProjectSecurityGroup", "SecurityGroup"])
class ProjectSecurityGroup(
        role: Role,
        grants: Map<PropName, Perm>,
        members: MutableList<User>,
        var project: String?,
) : SecurityGroup(role, grants, members) {
    @Relationship(type = "project")
    @JsonIgnore
    var _project: StringProp? = null// TODO: update when project model is created
}