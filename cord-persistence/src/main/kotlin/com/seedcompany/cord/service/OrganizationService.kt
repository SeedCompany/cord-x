package com.seedcompany.cord.service

import com.seedcompany.cord.dto.*
import com.seedcompany.cord.model.Organization
import com.seedcompany.cord.repository.OrganizationActiveReadOnlyRepository
import com.seedcompany.cord.repository.OrganizationRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
@RequestMapping("/organization")
class OrganizationService (
        val orgRepo: OrganizationRepository,
        val orgActiveReadOnlyRepo: OrganizationActiveReadOnlyRepository,
){
    @PostMapping("/create")
    suspend fun create(@RequestBody request: Organization): CreateOut {
        val org = Organization(
                address = request.address,
                name = request.name,
        )
        orgRepo.save(org).awaitFirstOrNull()

        return CreateOut(id = org.id, success = true)
    }

    @PostMapping("/read")
    suspend fun read(@RequestBody request: ReadIn): OrgOut {
        val org = orgActiveReadOnlyRepo.findById(request.id).awaitFirstOrNull()
                ?: return OrgOut(message = "org not found")
        return OrgOut(org, true)
    }

    @PostMapping("/update")
    suspend fun update(@RequestBody request: Organization): GenericOut {

        val org = orgRepo.findById(request.id).awaitFirstOrNull()
                ?: return GenericOut(message = "org not found")

        if (request.address != null) org.address = request.address
        if (request.name != null) org.name = request.name

        org.modifiedAt = ZonedDateTime.now()

        orgRepo.save(org).awaitFirstOrNull()

        return GenericOut(true)
    }

    @PostMapping("/delete")
    suspend fun delete(@RequestBody request: ReadIn): GenericOut {

        val org = orgRepo.deleteById(request.id).awaitFirstOrNull()
                ?: return GenericOut(message = "org not found")

        return GenericOut(true)
    }

}