package com.seedcompany.cord.dto

import com.seedcompany.cord.frontend.ApiUser
import com.seedcompany.cord.model.Power
import com.seedcompany.cord.model.User
import java.time.ZonedDateTime

class LoginGetCredsIn(
        val token: String,
        val email: String,
)

class PashOut(
        val pash: String? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)

class RegisterIn(
    val user: User,
    val token: String,
)

class RegisterOut(
        val user: ApiUser? = null,
        val powers: List<Power>? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)

class ResetPasswordIn(
        val emailToken: String,
        val email: String,
        val password: String,
)
class SetPasswordIn(
        val id: String,
        val passowrd: String,
)

class VerifyEmailIn(
        val email: String,
)

class EmailTokenCreateIn(
        val id: String,
        val email: String,
)

class EmailTokenOut(
        val email: String? = null,
        val token: String? = null,
        val createdAt: ZonedDateTime? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)