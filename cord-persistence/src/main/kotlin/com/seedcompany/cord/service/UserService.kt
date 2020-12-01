package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.model.BaseNodeLabel
import com.seedcompany.cord.repository.UserRepository
import com.seedcompany.cord.model.User
import com.seedcompany.cord.repository.UserActiveReadOnlyRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@RestController
@RequestMapping("/user")
class UserService(
        val userRepo: UserRepository,
        val userActiveReadOnlyRepo: UserActiveReadOnlyRepository,
        val authorizationService: AuthorizationService,
){

    @PostMapping("/create")
    suspend fun create(@RequestBody request: User): CreateOut {

        val user = User(
                about = request.about,
                displayFirstName = request.displayFirstName,
                displayLastName = request.displayLastName,
                email = request.email,
                phone = request.phone,
                realFirstName = request.realFirstName,
                realLastName = request.realLastName,
                roles = request.roles,
                status = request.status,
                timezone = request.timezone,
                title = request.title,
        )

        userRepo.save(user).awaitFirstOrNull()

        authorizationService.processBaseNode(ProcessBaseNodeIn(
                baseNodeId = user.id,
                label = BaseNodeLabel.User,
                creatorUserId = user.id,
        ))

        return CreateOut(id = user.id, success = true)
    }

    @PostMapping("/read")
    suspend fun read(@RequestBody request: UserReadIn): UserOut {
        val user = userActiveReadOnlyRepo.findById(request.id).awaitFirstOrNull()
                ?: return UserOut(message = "user not found")
        return UserOut(user, true)
    }

    @PostMapping("/createRead")
    suspend fun createRead(@RequestBody request: User): UserOut? {
        val id = this.create(request).id
        return read(UserReadIn(id))
    }

    @PostMapping("/update")
    suspend fun update(@RequestBody request: User): GenericOut {

        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return UserOut(message = "user not found")

        if (request.about != null) user.about = request.about
        if (request.displayFirstName != null) user.displayFirstName = request.displayFirstName
        if (request.displayLastName != null) user.displayLastName = request.displayLastName
        if (request.email != null) user.email = request.email
        if (request.phone != null) user.phone = request.phone
        if (request.realFirstName != null) user.realFirstName = request.realFirstName
        if (request.realLastName != null) user.realLastName = request.realLastName
        if (request.roles != null) user.roles = request.roles
        if (request.status != null) user.status = request.status
        if (request.timezone != null) user.timezone = request.timezone
        if (request.title != null) user.title = request.title

        user.modifiedAt = ZonedDateTime.now()

        userRepo.save(user).awaitFirstOrNull()

        return GenericOut(true)
    }
}