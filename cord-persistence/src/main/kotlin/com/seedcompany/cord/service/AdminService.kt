package com.seedcompany.cord.service

import com.seedcompany.cord.common.AllRoles
import com.seedcompany.cord.dto.BootstrapIn
import com.seedcompany.cord.dto.BootstrapOut
import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.dto.ReplaceIdIn
import com.seedcompany.cord.model.*
import com.seedcompany.cord.repository.*
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/admin")
class AdminService(
        val baseRepo: BaseNodeRepository,
        val orgRepo: OrganizationRepository,
        val orgActiveReadOnlyRepo: OrganizationActiveReadOnlyRepository,
        val userRepo: UserRepository,
        val userActiveReadOnlyRepo: UserActiveReadOnlyRepository,
        val globalSgRepo: GlobalSecurityGroupRepository,
        val globalSgActiveReadOnlyRepo: GlobalSecurityGroupActiveReadOnlyRepository,
) {
    @PostMapping("/replaceId")
    suspend fun replaceId(@RequestBody request: ReplaceIdIn): GenericOut {
        val node = baseRepo.replaceId(oldId = request.oldId, newId = request.newId).awaitFirstOrNull()
        return if (node == null) {
            GenericOut(message = "something went wrong")
        } else {
            GenericOut(true)
        }
    }

    @PostMapping("/bootstrap")
    suspend fun bootstrap(@RequestBody request: BootstrapIn): BootstrapOut {

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

        // default org
        val org = orgRepo.findByName(request.defaultOrgName).awaitFirstOrNull()

        if (org == null) {
            val newOrg = Organization(
                    address = "",
                    name = request.defaultOrgName,
            )
            newOrg.id = request.defaultOrgId
            orgRepo.save(newOrg).awaitFirstOrNull()
        }

        // root user
        var rootUser = userRepo.findByEmail(request.rootEmail).awaitFirstOrNull()

        if (rootUser == null) {
            rootUser = User(
                    about = "",
                    displayFirstName = "root",
                    displayLastName = "root",
                    email = request.rootEmail,
                    password = request.rootPash,
                    phone = "+1 (817) 557-2121", // Seed office
                    realFirstName = "root",
                    realLastName = "root",
                    roles = mutableListOf(Role.Administrator),
                    status = UserStatus.Active,
                    timezone = null,
                    title = "root"
            )

            val adminSg = globalSgRepo.findByGlobalRole(Role.Administrator).awaitFirst()
            adminSg.members.add(rootUser)
            globalSgRepo.save(adminSg).awaitFirstOrNull()
        }

        return BootstrapOut(success = true, rootAdminId = rootUser.id)
    }
}