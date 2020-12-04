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
    @PostMapping("/logout")
    suspend fun logout(@RequestBody request: ReadIn): GenericOut {
        val token = tokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return GenericOut(message = "token not found", error = ErrorCode.ID_NOT_FOUND)
        token.user = null
        tokenRepo.save(token).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/token/create")
    suspend fun readGlobalPermissions(@RequestBody request: TokenIn): GenericOut {
        val token = Token(id = request.value)
        tokenRepo.save(token).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/register")
    suspend fun register(@RequestBody request: TokenIn): GenericOut{

        return GenericOut(true)
    }
}