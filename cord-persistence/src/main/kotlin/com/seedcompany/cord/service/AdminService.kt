package com.seedcompany.cord.service

import com.seedcompany.cord.common.AllRoles
import com.seedcompany.cord.dto.BootstrapIn
import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.model.*
import com.seedcompany.cord.repository.GlobalSecurityGroupActiveReadOnlyRepository
import com.seedcompany.cord.repository.GlobalSecurityGroupRepository
import com.seedcompany.cord.repository.UserActiveReadOnlyRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.apache.commons.collections4.ListUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/admin")
class AdminService(
        val userRepo: UserRepository,
        val userActiveReadOnlyRepo: UserActiveReadOnlyRepository,
        val globalSgRepo: GlobalSecurityGroupRepository,
        val globalSgActiveReadOnlyRepo: GlobalSecurityGroupActiveReadOnlyRepository,
) {

    @PostMapping("/bootstrap")
    suspend fun bootstrap(@RequestBody request: BootstrapIn): GenericOut {

        // global SGs/roles
        GlobalRole.values().forEach {
            val sg = globalSgActiveReadOnlyRepo.findByGlobalRole(Role.valueOf(it.name)).awaitFirstOrNull()

            if (sg == null) {
                val newSg = GlobalSecurityGroup(
                        role = Role.valueOf(it.name),
                        grants = AllRoles.grants(Role.valueOf(it.name)),
                        members = mutableListOf()
                )
                globalSgRepo.save(newSg).awaitFirstOrNull()
            }
        }

        // root user
        val rootUser = userActiveReadOnlyRepo.findByEmail(request.rootEmail).awaitFirstOrNull()

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

            val adminSg = globalSgRepo.findByGlobalRole(Role.Administrator).awaitFirst()
            adminSg.members.add(newRootUser)
            globalSgRepo.save(adminSg).awaitFirstOrNull()
        }


        return GenericOut(true)
    }
}