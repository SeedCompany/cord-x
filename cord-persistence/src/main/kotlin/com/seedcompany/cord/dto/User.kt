package com.seedcompany.cord.dto

import com.seedcompany.cord.model.User

data class UserOut(
        val user: User? = null,
        override val success: Boolean = false,
        override val error: ErrorCode = ErrorCode.NO_ERROR,
        override val message: String? = null,
) : GenericOut(
        success = success,
        error = error,
        message = message,
)

data class UserCreateIn(val name: String)

data class UserUpdateIn(
        val id: String,
        val name: String,
)
