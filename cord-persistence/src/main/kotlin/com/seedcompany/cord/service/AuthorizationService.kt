package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.model.Perm
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

        user.membershipsGlobal.forEach{
            it.grants.forEach { (propName, perm) ->
                if (grants.containsKey(propName)){
                    if (grants[propName]!! < perm) grants[propName] = perm
                } else {
                    grants[propName] = perm
                }
            }
        }

        return GlobalPermissionsOut(success = true, grants = grants)
    }

}