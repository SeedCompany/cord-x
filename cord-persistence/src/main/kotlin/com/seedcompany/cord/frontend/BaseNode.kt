package com.seedcompany.cord.frontend

import java.time.ZonedDateTime

open class BaseNode {
    var id: SecureProperty<String>? = null
    var createdAt: SecureProperty<ZonedDateTime>? = null
}