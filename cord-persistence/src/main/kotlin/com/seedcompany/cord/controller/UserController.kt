package com.seedcompany.cord.controller

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.repository.UserRepository
import com.seedcompany.cord.model.User
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
        val userRepo: UserRepository
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
        return CreateOut(id = user.id, success = true)
    }

    @PostMapping("/read")
    suspend fun read(@RequestBody request: UserReadIn): UserOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return UserOut(message = "user not found")
        return UserOut(user, true)
    }

    @PostMapping("/createRead")
    suspend fun createRead(@RequestBody request: User): UserOut? {
        val id = this.create(request).id
        return read(UserReadIn(id))
    }

    @PostMapping("/update")
    suspend fun update(@RequestBody request: UserUpdateIn): GenericOut {


        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return UserOut(message = "user not found")
        user.about = request.name
        userRepo.save(user).awaitFirstOrNull()


        return GenericOut(true)
    }
}