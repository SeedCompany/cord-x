package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node

data class AllProperties(
        val UserAbout: Perm?,
        val UserDisplayFirstName: Perm?,
        val UserDisplayLastName: Perm?,
        val UserEmail: Perm?,
        val UserPhone: Perm?,
        val UserRealFirstName: Perm?,
        val UserRealLastName: Perm?,
        val UserRoles: Perm?,
        val UserStatus: Perm?,
        val UserTimezone: Perm?,
        val UserTitle: Perm?,
)

@Node
open class SecurityGroup(
        val role: DbRole,
        val grants: AllProperties?,
) : BaseNode()

@Node
class PublicSecurityGroup(
        role: DbRole,
        grants: AllProperties?,
): SecurityGroup(role, grants)

@Node
class OrgSecurityGroup(
        role: DbRole,
        grants: AllProperties?,
): SecurityGroup(role, grants)