package com.seedcompany.cord.frontend

class FeSecureProperty<T>(
        var value: T? = null,
        var canRead: Boolean = false,
        var canEdit: Boolean = false,
)