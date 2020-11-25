package com.seedcompany.cord.dto

import com.seedcompany.cord.model.User

data class UserOut(
        val user: User? = null,
        override val success: Boolean = false,
        override val message: String? = null,
) : GenericOut(success, message)

data class UserCreateIn(val name: String)

data class UserReadIn(val id: String)

data class UserUpdateIn(
        val id: String,
        val name: String,
)
