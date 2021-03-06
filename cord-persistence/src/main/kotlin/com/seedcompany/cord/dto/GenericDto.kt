package com.seedcompany.cord.dto


open class GenericOut (
        open val success: Boolean = false,
        open val error: ErrorCode = ErrorCode.NO_ERROR,
        open val message: String? = null,
)

open class IdOut (
        val id: String? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)

open class ReadIn(val id: String)