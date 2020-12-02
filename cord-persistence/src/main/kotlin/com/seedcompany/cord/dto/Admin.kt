package com.seedcompany.cord.dto

data class BootstrapIn (
    val rootEmail: String,
    val rootPash: String,
    val defaultOrgName: String,
    val defaultOrgId: String = "5c4278da9503d5cd78e82f02",
)

data class ReplaceIdIn (
        val oldId: String,
        val newId: String,
)