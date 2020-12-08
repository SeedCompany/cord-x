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
    var about: ApiSecureProperty<String> = ApiSecureProperty()
    var displayFirstName: ApiSecureProperty<String> = ApiSecureProperty()
    var displayLastName: ApiSecureProperty<String> = ApiSecureProperty()
    var education: ApiSecureProperty<String> = ApiSecureProperty()
    var email: ApiSecureProperty<String> = ApiSecureProperty()
    var phone: ApiSecureProperty<String> = ApiSecureProperty()
    var realFirstName: ApiSecureProperty<String> = ApiSecureProperty()
    var realLastName: ApiSecureProperty<String> = ApiSecureProperty()
    var roles: ApiSecureProperty<List<Role>> = ApiSecureProperty()
    var status: ApiSecureProperty<UserStatus> = ApiSecureProperty()
    var timezone: ApiSecureProperty<String> = ApiSecureProperty()
    var title: ApiSecureProperty<String> = ApiSecureProperty()
}
        