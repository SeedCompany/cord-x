package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.model.Perm
import com.seedcompany.cord.model.Power
import com.seedcompany.cord.model.PropName
import com.seedcompany.cord.repository.GlobalSecurityGroupRepository
import com.seedcompany.cord.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authorization")
class AuthorizationService(
        val userRepo: UserRepository,
        val authorizationRepo: GlobalSecurityGroupRepository,
) {

    @PostMapping("/readGlobalPermissions")
    suspend fun readGlobalPermissions(@RequestBody request: GlobalPermissionsIn): GlobalPermissionsOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return GlobalPermissionsOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)

        val grants: MutableMap<PropName, Perm> = mutableMapOf()
        val powers: MutableList<Power> = mutableListOf()

        user.membershipsGlobal.forEach{
            it.powers.forEach { power ->
                powers.add(power)
            }
            it.grants.forEach { (propName, perm) ->
                if (grants.containsKey(propName)){
                    if (grants[propName]!! < perm) grants[propName] = perm
                } else {
                    grants[propName] = perm
                }
            }
        }

        return GlobalPermissionsOut(success = true, powers = powers, grants = grants)
    }

    @PostMapping("/getPowers")
    suspend fun getPowers(@RequestBody request: ReadIn): GetPowersOut {
        val user = userRepo.findById(request.id).awaitFirstOrNull()
                ?: return GetPowersOut(message = "user not found", error = ErrorCode.ID_NOT_FOUND)
        val powers: MutableList<Power> = mutableListOf()
        user.membershipsGlobal.forEach{
            it.powers.forEach { power ->
                powers.add(power)
            }
        }
        return GetPowersOut(success = true, powers = powers)
    }

}