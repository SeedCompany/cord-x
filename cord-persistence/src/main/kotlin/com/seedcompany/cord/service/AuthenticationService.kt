package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.model.Token
import com.seedcompany.cord.repository.TokenRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authentication")
class AuthenticationService(
        val tokenRepo: TokenRepository,
) {

    @PostMapping("/token/create")
    suspend fun readGlobalPermissions(@RequestBody request: TokenIn): GenericOut {
        val token = Token(value = request.value)
        tokenRepo.save(token).awaitFirstOrNull()
        return GenericOut(true)
    }

}