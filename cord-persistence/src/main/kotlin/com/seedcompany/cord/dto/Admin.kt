package com.seedcompany.cord.dto

data class BootstrapIn (
    val rootEmail: String,
    val rootPash: String,
    val defaultOrgName: String,
)