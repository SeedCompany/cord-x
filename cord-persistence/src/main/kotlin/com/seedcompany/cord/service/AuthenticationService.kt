package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.frontend.ApiSecureReadIn
import com.seedcompany.cord.frontend.ApiUserOut
import com.seedcompany.cord.model.EmailToken
import com.seedcompany.cord.model.Token
import com.seedcompany.cord.model.User
import com.seedcompany.cord.repository.EmailTokenRepository
import com.seedcompany.cord.repository.TokenRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/authentication")
class AuthenticationService(
        val authorizationService: AuthorizationService,
        val emailTokenRepo: EmailTokenRepository,
        val tokenRepo: TokenRepository,
        val userRepo: UserRepository,
        val userService: UserService,
        val userApi: UserApi,
) {
    val saltLength = 16 // salt length in bytes
    val hashLength = 32 // hash length in bytes
    val parallelism = 1 // currently not supported by Spring Security
    val memory = 4096 // memory costs
    val iterations = 3

    val argon2PasswordEncoder = Argon2PasswordEncoder(
            saltLength,
            hashLength,
            parallelism,
            memory,
            iterations)

    @PostMapping("/emailTokenCreate")
    @Transactional
    suspend fun emailTokenCreate(@RequestBody request: EmailTokenCreateIn): GenericOut {
        val emailToken = EmailToken(id = request.id, email = request.email)
        emailTokenRepo.save(emailToken).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/getEmailToken")
    suspend fun getEmailToken(@RequestBody request: ReadIn): EmailTokenOut {
        val emailToken = emailTokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return EmailTokenOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)
        return EmailTokenOut(success = true, email = emailToken.email, token = emailToken.id, createdAt = emailToken.createdAt)
    }

    @PostMapping("/loginGetCreds")
    suspend fun loginGetCreds(@RequestBody request: LoginGetCredsIn): PashOut {
        val token = tokenRepo.findById(request.token).awaitFirstOrNull()
                ?: return PashOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)
        val user = userRepo.findByEmail(request.email).awaitFirstOrNull()
                ?: return PashOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)

        return PashOut(success = true, pash = user.password)
    }

    @PostMapping("/loginConnect")
    @Transactional
    suspend fun loginConnect(@RequestBody request: LoginGetCredsIn): IdOut {
        val token = tokenRepo.findById(request.token).awaitFirstOrNull()
                ?: return IdOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)
        val user = userRepo.findByEmail(request.email).awaitFirstOrNull()
                ?: return IdOut(message = "credentials not found", error = ErrorCode.ID_NOT_FOUND)

        tokenRepo.deleteTokenRelationships(token.id).awaitFirstOrNull()
        token.user = user
        tokenRepo.save(token).awaitFirstOrNull()

        return IdOut(success = true, id = user.id)
    }

    @PostMapping("/logout")
    @Transactional
    suspend fun logout(@RequestBody request: ReadIn): GenericOut {
        val token = tokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return GenericOut(message = "token not found", error = ErrorCode.ID_NOT_FOUND)
        tokenRepo.deleteTokenRelationships(token.id).awaitFirstOrNull()
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
    @Transactional
    suspend fun register(@RequestBody request: RegisterIn): RegisterOut {

        val pash = argon2PasswordEncoder.encode(request.user.password)
        val user = User(user = request.user, passwordHash = pash)
        val savedUser = userRepo.save(user).awaitFirstOrNull()
                ?: ApiUserOut(message = "something went wrong", error = ErrorCode.UNKNOWN_ERROR)
        loginConnect(LoginGetCredsIn(token = request.token, email = user.email!!))

        val apiUser = userApi.read(ApiSecureReadIn(id = user.id, requestorId = user.id))
        val powersOut = authorizationService.getPowers(ReadIn(id = user.id))
        return RegisterOut(user = apiUser.user, powers = powersOut.powers, success = true)
    }

    @PostMapping("/resetPassword")
    @Transactional
    suspend fun resetPassword(@RequestBody request: ResetPasswordIn): GenericOut {
        tokenRepo.deleteById(request.emailToken).awaitFirstOrNull()
        val user = userRepo.findByEmail(request.email).awaitFirstOrNull()
                ?: return PashOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)

        user.password = request.password
        userRepo.save(user).awaitFirstOrNull()

        return GenericOut(success = true)
    }

    @PostMapping("/setPassword")
    @Transactional
    suspend fun setPassword(@RequestBody request: SetPasswordIn): GenericOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return PashOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)

        user.password = request.passowrd
        userRepo.save(user).awaitFirstOrNull()

        return GenericOut(success = true)
    }

    @PostMapping("/tokenCreate")
    suspend fun readGlobalPermissions(@RequestBody request: ReadIn): GenericOut {
        val token = Token(id = request.id)
        tokenRepo.save(token).awaitFirstOrNull()
        return GenericOut(true)
    }

    @PostMapping("/verifyToken")
    suspend fun verifyToken(@RequestBody request: ReadIn): IdOut {
        val token = tokenRepo.findById(request.id).awaitFirstOrNull()
                ?: return IdOut(message = "token not found", error = ErrorCode.ID_NOT_FOUND)
        if (token.user == null) return IdOut(success = true)
        return IdOut(success = true, id = token.user!!.id)
    }

    @PostMapping("/verifyEmail")
    suspend fun verifyEmail(@RequestBody request: VerifyEmailIn): GenericOut {
        val user = userRepo.findByEmail(request.email).awaitFirstOrNull()
                ?: return GenericOut(message = "email not found", error = ErrorCode.ID_NOT_FOUND)
        return GenericOut(success = true)
    }
}