package com.seedcompany.cord.service

import com.seedcompany.cord.dto.ReadIn
import com.seedcompany.cord.dto.UserOut
import com.seedcompany.cord.frontend.SecureReadIn
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserApi(
        val userService: UserService,
) {
    @PostMapping("/read")
    suspend fun read(@RequestBody request: SecureReadIn): UserOut {
        // user is requesting their own profile
        if (request.id == request.requesterId) return userService.read(ReadIn(request.id))

        // get global permissions of requester
        return UserOut(success = true)

    }
}
