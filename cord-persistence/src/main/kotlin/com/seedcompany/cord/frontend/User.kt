package com.seedcompany.cord.frontend

import com.seedcompany.cord.model.Role
import com.seedcompany.cord.model.UserStatus
import java.time.ZonedDateTime

class User: BaseNode() {
    var email: SecureProperty<String> = SecureProperty()
    var realFirstName: SecureProperty<String> = SecureProperty()
    var realLastName: SecureProperty<String> = SecureProperty()
    var displayFirstName: SecureProperty<String> = SecureProperty()
    var displayLastName: SecureProperty<String> = SecureProperty()
    var phone: SecureProperty<String> = SecureProperty()
    var timezone: SecureProperty<String> = SecureProperty()
    var about: SecureProperty<String> = SecureProperty()
    var status: SecureProperty<UserStatus> = SecureProperty()
    var roles: SecureProperty<Role> = SecureProperty()
    var title: SecureProperty<String> = SecureProperty()
}
        