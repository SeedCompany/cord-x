package com.seedcompany.cord.dto

data class BootstrapIn (
    val rootEmail: String,
    val rootPash: String,
    val defaultOrgName: String,
    val defaultOrgId: String = "5c4278da9503d5cd78e82f02",
)

class BootstrapOut(
        val rootAdminId: String,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)

data class ReplaceIdIn (
        val oldId: String,
        val newId: String,
)