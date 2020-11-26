package com.seedcompany.cord.dto

import com.seedcompany.cord.model.BaseNodeLabel

data class ProcessBaseNodeIn(
        val baseNodeId: String,
        val label: BaseNodeLabel,
        val creatorUserId: String,
)
