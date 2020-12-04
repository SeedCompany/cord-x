package com.seedcompany.cord.service

import com.seedcompany.cord.common.AllRoles
import com.seedcompany.cord.dto.*
import com.seedcompany.cord.frontend.*
import com.seedcompany.cord.model.Perm
import com.seedcompany.cord.model.PropName
import com.seedcompany.cord.model.Role
import com.seedcompany.cord.repository.TokenRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserApi(
        val tokenRepo: TokenRepository,
        val userRepo: UserRepository,
        val userService: UserService,
        val authorizationService: AuthorizationService,
) {
    @PostMapping("/read")
    suspend fun read(@RequestBody request: SecureReadIn): ApiUserOut {

        var grants: Map<PropName, Perm>

        // if user isn't known, treat as anonymous request
        if (request.requestorId == null) grants = AllRoles.grants(Role.Anonymous)
            else {
                // get global permissions of requester
                grants = authorizationService.readGlobalPermissions(GlobalPermissionsIn((request.requestorId))).grants
                        ?: return ApiUserOut(message = "something went wrong")
        }

        // if the user is accessing their own profile, give them admin rights
        if (request.id == request.requestorId) grants = AllRoles.grants(Role.Administrator)

        if (grants.isEmpty()) return ApiUserOut(message = "no grants available to this user")

        val userDto = userService.read(ReadIn(request.id))
        if (userDto.user == null) return ApiUserOut(message = "user not found")

        val apiUser = ApiUser()
        apiUser.id = userDto.user.id
        apiUser.createdAt = userDto.user.createdAt

        apiUser.about.value = if (grants[PropName.UserAbout]?.canRead == true ) userDto.user.about else null
        apiUser.about.canRead = grants[PropName.UserAbout]?.canRead ?: false
        apiUser.about.canEdit = grants[PropName.UserAbout]?.canEdit ?: false

        apiUser.displayFirstName.value = if (grants[PropName.UserDisplayFirstName]?.canRead == true ) userDto.user.displayFirstName else null
        apiUser.displayFirstName.canRead = grants[PropName.UserDisplayFirstName]?.canRead ?: false
        apiUser.displayFirstName.canEdit = grants[PropName.UserDisplayFirstName]?.canEdit ?: false

        apiUser.displayLastName.value = if (grants[PropName.UserDisplayLastName]?.canRead == true ) userDto.user.displayLastName else null
        apiUser.displayLastName.canRead = grants[PropName.UserDisplayLastName]?.canRead ?: false
        apiUser.displayLastName.canEdit = grants[PropName.UserDisplayLastName]?.canEdit ?: false

        apiUser.education.value = "todo"
        apiUser.education.canRead = true
        apiUser.education.canEdit = true

        apiUser.email.value = if (grants[PropName.UserEmail]?.canRead == true ) userDto.user.email else null
        apiUser.email.canRead = grants[PropName.UserEmail]?.canRead ?: false
        apiUser.email.canEdit = grants[PropName.UserEmail]?.canEdit ?: false

        apiUser.phone.value = if (grants[PropName.UserPhone]?.canRead == true ) userDto.user.phone else null
        apiUser.phone.canRead = grants[PropName.UserPhone]?.canRead ?: false
        apiUser.phone.canEdit = grants[PropName.UserPhone]?.canEdit ?: false

        apiUser.realFirstName.value = if (grants[PropName.UserRealFirstName]?.canRead == true ) userDto.user.realFirstName else null
        apiUser.realFirstName.canRead = grants[PropName.UserRealFirstName]?.canRead ?: false
        apiUser.realFirstName.canEdit = grants[PropName.UserRealFirstName]?.canEdit ?: false

        apiUser.realLastName.value = if (grants[PropName.UserRealLastName]?.canRead == true ) userDto.user.realLastName else null
        apiUser.realLastName.canRead = grants[PropName.UserRealLastName]?.canRead ?: false
        apiUser.realLastName.canEdit = grants[PropName.UserRealLastName]?.canEdit ?: false

        apiUser.roles.value = if (grants[PropName.UserRoles]?.canRead == true ) userDto.user.roles else null
        apiUser.roles.canRead = grants[PropName.UserRoles]?.canRead ?: false
        apiUser.roles.canEdit = grants[PropName.UserRoles]?.canEdit ?: false

        apiUser.status.value = if (grants[PropName.UserStatus]?.canRead == true ) userDto.user.status else null
        apiUser.status.canRead = grants[PropName.UserStatus]?.canRead ?: false
        apiUser.status.canEdit = grants[PropName.UserStatus]?.canEdit ?: false

        apiUser.timezone.value = if (grants[PropName.UserTimezone]?.canRead == true ) userDto.user.timezone else null
        apiUser.timezone.canRead = grants[PropName.UserTimezone]?.canRead ?: false
        apiUser.timezone.canEdit = grants[PropName.UserTimezone]?.canEdit ?: false

        apiUser.title.value = if (grants[PropName.UserTitle]?.canRead == true ) userDto.user.title else null
        apiUser.title.canRead = grants[PropName.UserTitle]?.canRead ?: false
        apiUser.title.canEdit = grants[PropName.UserTitle]?.canEdit ?: false

        return ApiUserOut(success = true, user = apiUser)

    }

    @PostMapping("/userFromTokenUnsafe")
    suspend fun userFromTokenUnsafe(@RequestBody request: ReadIn): ApiUserOut{
        val token = tokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return ApiUserOut(message = "token not found", error = ErrorCode.ID_NOT_FOUND)
        if (token.user == null) return ApiUserOut(message = "token not in use")
        return read(SecureReadIn(id = token.user!!.id, requestorId = token.user!!.id))
    }

    @PostMapping("/update")
    @Transactional
    suspend fun update(@RequestBody request: ApiUserUpdateIn): ApiUserOut {

        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return ApiUserOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)

        // get global permissions of requester
        val grants = authorizationService.readGlobalPermissions(GlobalPermissionsIn((request.requestorId))).grants
                ?: return ApiUserOut(message = "something went wrong")

        // update properties the user has edit permission on
        if (grants[PropName.UserAbout]?.canEdit == true) user.about = request.user.about
        if (grants[PropName.UserDisplayFirstName]?.canEdit == true) user.displayFirstName = request.user.displayFirstName
        if (grants[PropName.UserDisplayLastName]?.canEdit == true) user.displayLastName = request.user.displayLastName
        if (grants[PropName.UserEmail]?.canEdit == true) user.email = request.user.email
        if (grants[PropName.UserPhone]?.canEdit == true) user.phone = request.user.phone
        if (grants[PropName.UserRealFirstName]?.canEdit == true) user.realFirstName = request.user.realFirstName
        if (grants[PropName.UserRealLastName]?.canEdit == true) user.realLastName = request.user.realLastName
        if (grants[PropName.UserRoles]?.canEdit == true) user.roles = request.user.roles
        if (grants[PropName.UserStatus]?.canEdit == true) user.status = request.user.status
        if (grants[PropName.UserTimezone]?.canEdit == true) user.timezone = request.user.timezone
        if (grants[PropName.UserTitle]?.canEdit == true) user.title = request.user.title

        userRepo.save(user).awaitFirstOrNull()

        return read(SecureReadIn(id = request.id, requestorId = request.requestorId))
    }

}
