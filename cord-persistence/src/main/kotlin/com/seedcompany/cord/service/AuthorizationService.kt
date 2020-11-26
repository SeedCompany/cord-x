package com.seedcompany.cord.service

import com.seedcompany.cord.dto.GenericOut
import com.seedcompany.cord.dto.ProcessBaseNodeIn
import com.seedcompany.cord.model.BaseNodeLabel
import com.seedcompany.cord.model.DbRole
import com.seedcompany.cord.model.SecurityGroup
import com.seedcompany.cord.repository.AuthorizationRepository
import com.seedcompany.cord.repository.UserRepository
import com.seedcompany.cord.role.AdministratorRole
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authorization")
class AuthorizationService(
        val userRepo: UserRepository,
        val authorizationRepo: AuthorizationRepository,
) {

    @PostMapping("/processBaseNode")
    suspend fun processBaseNode(@RequestBody request: ProcessBaseNodeIn): GenericOut {

        if (request.label == BaseNodeLabel.User) {
            val user = userRepo.findById(request.baseNodeId).awaitFirstOrNull()
                    ?: return GenericOut(message = "failed to find new user node")
            val adminSg = SecurityGroup(
                    role = DbRole.AdministratorRole,
                    grants = AdministratorRole().grants(),
            )
            adminSg.members.add(user)
            adminSg.baseNodes.add(user)
            authorizationRepo.save(adminSg).awaitFirstOrNull()
        }

        return GenericOut(true)
    }

}