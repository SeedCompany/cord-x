package com.seedcompany.cord.dto

import com.seedcompany.cord.model.Organization

data class OrgOut(
        val org: Organization? = null,
        override val success: Boolean = false,
        override val error: ErrorCode = ErrorCode.NO_ERROR,
        override val message: String? = null,
) : GenericOut(
        success = success,
        error = error,
        message = message,
)