package com.seedcompany.cord.dto

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