package com.seedcompany.cord.role

import com.seedcompany.cord.model.*

object CordUser : IRole {
    override fun name() = Role.CordUser
    override fun powers() = listOf<Power>()
    override fun grants() = mapOf(
            PropName.UserAbout to Perm.READ_WRITE_DELETE,
            PropName.UserDisplayFirstName to Perm.READ_WRITE_DELETE,
            PropName.UserDisplayLastName to Perm.READ_WRITE_DELETE,
            PropName.UserEmail to Perm.READ_WRITE_DELETE,
            PropName.UserPhone to Perm.READ_WRITE_DELETE,
            PropName.UserRealFirstName to Perm.READ_WRITE_DELETE,
            PropName.UserRealLastName to Perm.READ_WRITE_DELETE,
            PropName.UserRoles to Perm.READ_WRITE_DELETE,
            PropName.UserStatus to Perm.READ_WRITE_DELETE,
            PropName.UserTimezone to Perm.READ_WRITE_DELETE,
            PropName.UserTitle to Perm.READ_WRITE_DELETE,
    )
}
