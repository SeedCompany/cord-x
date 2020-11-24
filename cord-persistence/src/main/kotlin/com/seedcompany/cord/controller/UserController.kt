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
    suspend fun create(@RequestBody request: UserCreateIn): UserCreateOut? {
        val user = User(request.name)
        userRepo.save(user).awaitFirstOrNull()
        return UserCreateOut(true)
    }

    @PostMapping("/get")
    suspend fun getOne(@RequestBody request: UserReadIn): UserReadOut? {
        val user = userRepo.findById(request.id).awaitFirstOrNull() ?: return null
        return UserReadOut(UserDto(user.name))
    }
}