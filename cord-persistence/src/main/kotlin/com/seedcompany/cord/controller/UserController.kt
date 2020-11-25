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
    suspend fun create(@RequestBody request: UserCreateIn): GenericOut? {
        val user = User(request.name)
        userRepo.save(user).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/read")
    suspend fun read(@RequestBody request: UserReadIn): UserOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return UserOut(message = "user not found")
        return UserOut(user, true)
    }

    @PostMapping("/update")
    suspend fun update(@RequestBody request: UserUpdateIn): GenericOut {


        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return UserOut(message = "user not found")
        user.name = request.name
        userRepo.save(user).awaitFirstOrNull()


        return GenericOut(true)
    }
}