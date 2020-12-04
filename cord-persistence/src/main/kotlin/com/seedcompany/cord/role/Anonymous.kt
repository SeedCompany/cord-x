package com.seedcompany.cord.role

import com.seedcompany.cord.model.Role
import com.seedcompany.cord.model.IRole
import com.seedcompany.cord.model.Perm
import com.seedcompany.cord.model.PropName

object Anonymous : IRole {
    override fun name() = Role.Anonymous
    override fun grants() = mapOf(
            PropName.UserDisplayFirstName to Perm.READ,
            PropName.UserDisplayLastName to Perm.READ,
    )
}
