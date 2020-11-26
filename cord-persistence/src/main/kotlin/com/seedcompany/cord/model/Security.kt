package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.CompositeProperty
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

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

@Node
open class SecurityGroup(
        var role: DbRole,
        @CompositeProperty
        var grants: Map<PropName, Perm>,
) : BaseNode() {

    @Relationship(type = "member")
    @JsonIgnore
    var members: MutableList<User> = mutableListOf()

    @Relationship(type = "baseNode")
    @JsonIgnore
    var baseNodes: MutableList<BaseNode> = mutableListOf()

}

@Node
class PublicSecurityGroup(
        role: DbRole,
        grants: Map<PropName, Perm>,
) : SecurityGroup(role, grants)

@Node
class OrgSecurityGroup(
        role: DbRole,
        grants: Map<PropName, Perm>,
) : SecurityGroup(role, grants)