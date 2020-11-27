package com.seedcompany.cord.service

import com.seedcompany.cord.dto.BootstrapIn
import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.repository.AuthorizationRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminService (
        val userRepo: UserRepository,
        val authorizationRepo: AuthorizationRepository,
){

    @PostMapping("/bootstrap")
    suspend fun bootstrap(@RequestBody request: BootstrapIn): GenericOut {

        val rootUser = userRepo.findByEmail(request.rootEmail).awaitFirstOrNull()

//        println(rootUser?.id)

        return GenericOut(true)
    }
}