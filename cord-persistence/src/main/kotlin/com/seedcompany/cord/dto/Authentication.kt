package com.seedcompany.cord.dto

class TokenIn(
        var value: String,
)

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