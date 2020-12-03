package com.seedcompany.cord.frontend

class SecureProperty<T>(
        val value: T? = null,
        val canRead: Boolean = false,
        val canEdit: Boolean = false,
)