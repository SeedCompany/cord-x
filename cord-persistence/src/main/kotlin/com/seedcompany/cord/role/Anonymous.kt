package com.seedcompany.cord.role

import com.seedcompany.cord.model.*

object Anonymous : IRole {
    override fun name() = Role.Anonymous
    override fun powers() = listOf<Power>()
    override fun grants() = mapOf(
            PropName.UserDisplayFirstName to Perm.READ,
            PropName.UserDisplayLastName to Perm.READ,
    )
}
