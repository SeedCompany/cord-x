package com.seedcompany.cord.service

import com.seedcompany.cord.common.AllRoles
import com.seedcompany.cord.dto.BootstrapIn
import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.model.*
import com.seedcompany.cord.repository.AuthorizationRepository
import com.seedcompany.cord.repository.UserActiveRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/admin")
class AdminService(
        val userRepo: UserRepository,
        val userActiveRepo: UserActiveRepository,
        val authorizationRepo: AuthorizationRepository,
) {

    @PostMapping("/bootstrap")
    suspend fun bootstrap(@RequestBody request: BootstrapIn): GenericOut {

        // root user
        val rootUser = userActiveRepo.findByEmail(request.rootEmail).awaitFirstOrNull()

        if (rootUser == null){
            val newRootUser = User(
                    about = "",
                    displayFirstName = "root",
                    displayLastName = "root",
                    email = request.rootEmail,
                    phone = "+1 (817) 557-2121",
                    realFirstName = "root",
                    realLastName = "root",
                    roles = listOf(Role.Administrator),
                    status = UserStatus.Active,
                    timezone = TimeZone.getDefault().toString(),
                    title = "root"
            )
            userRepo.save(newRootUser).awaitFirstOrNull()
        }

        // global SGs/roles
        GlobalRole.values().forEach {
            val sg = authorizationRepo.findByGlobalRole(Role.valueOf(it.name)).awaitFirstOrNull() ?: GlobalSecurityGroup(
                    role = Role.valueOf(it.name),
                    grants = AllRoles.grants(Role.valueOf(it.name))
            )
            authorizationRepo.save(sg).awaitFirstOrNull()
        }

        return GenericOut(true)
    }
}