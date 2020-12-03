package com.seedcompany.cord.frontend

import com.seedcompany.cord.dto.ErrorCode
import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.model.Role
import com.seedcompany.cord.model.User
import com.seedcompany.cord.model.UserStatus

class ApiUserOut(
        var user: ApiUser? = null,
        success: Boolean = false,
        error: ErrorCode = ErrorCode.NO_ERROR,
        message: String? = null,
) : GenericOut(success, error, message)

class ApiUserUpdateIn(
        var user: User,
        var id: String,
        var requestorId: String,
)

class ApiUser: ApiBaseNode() {
    var about: FeSecureProperty<String> = FeSecureProperty()
    var displayFirstName: FeSecureProperty<String> = FeSecureProperty()
    var displayLastName: FeSecureProperty<String> = FeSecureProperty()
    var education: FeSecureProperty<String> = FeSecureProperty()
    var email: FeSecureProperty<String> = FeSecureProperty()
    var phone: FeSecureProperty<String> = FeSecureProperty()
    var realFirstName: FeSecureProperty<String> = FeSecureProperty()
    var realLastName: FeSecureProperty<String> = FeSecureProperty()
    var roles: FeSecureProperty<List<Role>> = FeSecureProperty()
    var status: FeSecureProperty<UserStatus> = FeSecureProperty()
    var timezone: FeSecureProperty<String> = FeSecureProperty()
    var title: FeSecureProperty<String> = FeSecureProperty()
}
        