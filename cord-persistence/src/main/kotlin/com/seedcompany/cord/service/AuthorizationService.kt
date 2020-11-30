package com.seedcompany.cord.service

import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.dto.ProcessBaseNodeIn
import com.seedcompany.cord.repository.AuthorizationRepository
import com.seedcompany.cord.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authorization")
class AuthorizationService(
        val userRepo: UserRepository,
        val authorizationRepo: AuthorizationRepository,
) {

    @PostMapping("/processBaseNode")
    suspend fun processBaseNode(@RequestBody request: ProcessBaseNodeIn): GenericOut {

        return GenericOut(true)
    }

}