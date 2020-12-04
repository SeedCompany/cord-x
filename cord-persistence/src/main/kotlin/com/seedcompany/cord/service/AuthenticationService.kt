package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.model.Token
import com.seedcompany.cord.model.User
import com.seedcompany.cord.repository.TokenRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authentication")
class AuthenticationService(
        val tokenRepo: TokenRepository,
        val userRepo: UserRepository,
        val userService: UserService,
) {
    @PostMapping("/login/getCreds")
    suspend fun loginGetCreds(@RequestBody request: LoginGetCredsIn): PashOut {
        val token = tokenRepo.findById(request.token).awaitFirstOrNull()
                ?: return PashOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)
        val user = userRepo.findByEmail(request.email).awaitFirstOrNull()
                ?: return PashOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)

        return PashOut(success = true, pash = user.password)
    }

    @PostMapping("/login/connect")
    suspend fun loginConnect(@RequestBody request: LoginGetCredsIn): IdOut {
        val token = tokenRepo.findById(request.token).awaitFirstOrNull()
                ?: return IdOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)
        val user = userRepo.findByEmail(request.email).awaitFirstOrNull()
                ?: return IdOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)

        token.user = user
        tokenRepo.save(token).awaitFirstOrNull()

        return IdOut(success = true, id = user.id)
    }

    @PostMapping("/logout")
    suspend fun logout(@RequestBody request: ReadIn): GenericOut {
        val token = tokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return GenericOut(message = "token not found", error = ErrorCode.ID_NOT_FOUND)
        token.user = null
        tokenRepo.save(token).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/pashByUserId")
    suspend fun pashByUserId(@RequestBody request: ReadIn): PashOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return PashOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)

        return PashOut(success = true, pash = user.password)
    }

    @PostMapping("/register")
    suspend fun register(@RequestBody request: User): GenericOut{
        val newUser = userService.create(request)

        return GenericOut(true)
    }

    @PostMapping("/setPassword")
    suspend fun setPassword(@RequestBody request: SetPasswordIn): GenericOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return PashOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)

        user.password = request.passowrd
        userRepo.save(user).awaitFirstOrNull()

        return GenericOut(success = true)
    }

    @PostMapping("/token/create")
    suspend fun readGlobalPermissions(@RequestBody request: TokenIn): GenericOut {
        val token = Token(id = request.value)
        tokenRepo.save(token).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/verifyToken")
    suspend fun verifyToken(@RequestBody request: ReadIn): GenericOut {
        val token = tokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return GenericOut(message = "token not found", error = ErrorCode.ID_NOT_FOUND)
        return GenericOut(success = true)
    }
}