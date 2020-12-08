package com.seedcompany.cord.dto

import com.seedcompany.cord.model.BaseNodeLabel
import com.seedcompany.cord.model.Perm
import com.seedcompany.cord.model.Power
import com.seedcompany.cord.model.PropName

data class ProcessBaseNodeIn(
        val baseNodeId: String,
        val label: BaseNodeLabel,
        val creatorUserId: String,
)

data class GlobalPermissionsIn(
        val id: String,
)

class GlobalPermissionsOut(
        val id: String? = null,
        val powers: List<Power>? = null,
        val grants: Map<PropName, Perm>? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)

class GetPowersOut(
        val powers: List<Power>? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)