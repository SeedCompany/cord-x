package com.seedcompany.cord.service

import com.seedcompany.cord.dto.GlobalPermissionsIn
import com.seedcompany.cord.dto.ReadIn
import com.seedcompany.cord.frontend.SecureReadIn
import com.seedcompany.cord.frontend.FeUser
import com.seedcompany.cord.frontend.FeUserOut
import com.seedcompany.cord.model.PropName
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserApi(
        val userService: UserService,
        val authorizationService: AuthorizationService,
) {
    @PostMapping("/read")
    suspend fun read(@RequestBody request: SecureReadIn): FeUserOut {

        // get global permissions of requester
        val grants = authorizationService.readGlobalPermissions(GlobalPermissionsIn((request.requestorId))).grants
                ?: return FeUserOut(message = "something went wrong")

        if (grants.isEmpty()) return FeUserOut(message = "no grants available to this user")

        val userDto = userService.read(ReadIn(request.id))
        if (userDto.user == null) return FeUserOut(message = "user not found")

        val feUser = FeUser()
        feUser.id = userDto.user.id
        feUser.createdAt = userDto.user.createdAt

        feUser.about.value = if (grants[PropName.UserAbout]?.canRead == true ) userDto.user.about else null
        feUser.about.canRead = grants[PropName.UserAbout]?.canRead ?: false
        feUser.about.canEdit = grants[PropName.UserAbout]?.canEdit ?: false

        feUser.displayFirstName.value = if (grants[PropName.UserDisplayFirstName]?.canRead == true ) userDto.user.displayFirstName else null
        feUser.displayFirstName.canRead = grants[PropName.UserDisplayFirstName]?.canRead ?: false
        feUser.displayFirstName.canEdit = grants[PropName.UserDisplayFirstName]?.canEdit ?: false

        feUser.displayLastName.value = if (grants[PropName.UserDisplayLastName]?.canRead == true ) userDto.user.displayLastName else null
        feUser.displayLastName.canRead = grants[PropName.UserDisplayLastName]?.canRead ?: false
        feUser.displayLastName.canEdit = grants[PropName.UserDisplayLastName]?.canEdit ?: false

        feUser.education.value = "todo"
        feUser.education.canRead = true
        feUser.education.canEdit = true

        feUser.email.value = if (grants[PropName.UserEmail]?.canRead == true ) userDto.user.email else null
        feUser.email.canRead = grants[PropName.UserEmail]?.canRead ?: false
        feUser.email.canEdit = grants[PropName.UserEmail]?.canEdit ?: false

        feUser.phone.value = if (grants[PropName.UserPhone]?.canRead == true ) userDto.user.phone else null
        feUser.phone.canRead = grants[PropName.UserPhone]?.canRead ?: false
        feUser.phone.canEdit = grants[PropName.UserPhone]?.canEdit ?: false

        feUser.realFirstName.value = if (grants[PropName.UserRealFirstName]?.canRead == true ) userDto.user.realFirstName else null
        feUser.realFirstName.canRead = grants[PropName.UserRealFirstName]?.canRead ?: false
        feUser.realFirstName.canEdit = grants[PropName.UserRealFirstName]?.canEdit ?: false

        feUser.realLastName.value = if (grants[PropName.UserRealLastName]?.canRead == true ) userDto.user.realLastName else null
        feUser.realLastName.canRead = grants[PropName.UserRealLastName]?.canRead ?: false
        feUser.realLastName.canEdit = grants[PropName.UserRealLastName]?.canEdit ?: false

        feUser.roles.value = if (grants[PropName.UserRoles]?.canRead == true ) userDto.user.roles else null
        feUser.roles.canRead = grants[PropName.UserRoles]?.canRead ?: false
        feUser.roles.canEdit = grants[PropName.UserRoles]?.canEdit ?: false

        feUser.status.value = if (grants[PropName.UserStatus]?.canRead == true ) userDto.user.status else null
        feUser.status.canRead = grants[PropName.UserStatus]?.canRead ?: false
        feUser.status.canEdit = grants[PropName.UserStatus]?.canEdit ?: false

        feUser.timezone.value = if (grants[PropName.UserTimezone]?.canRead == true ) userDto.user.timezone else null
        feUser.timezone.canRead = grants[PropName.UserTimezone]?.canRead ?: false
        feUser.timezone.canEdit = grants[PropName.UserTimezone]?.canEdit ?: false

        feUser.title.value = if (grants[PropName.UserTitle]?.canRead == true ) userDto.user.title else null
        feUser.title.canRead = grants[PropName.UserTitle]?.canRead ?: false
        feUser.title.canEdit = grants[PropName.UserTitle]?.canEdit ?: false

        return FeUserOut(success = true, user = feUser)

    }
}
