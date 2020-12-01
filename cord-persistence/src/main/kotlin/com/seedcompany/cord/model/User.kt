package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

interface UserActiveReadOnly {
    fun getAbout()
    fun getDisplayFirstName()
    fun getDisplayLastName()
    fun getEmail()
    fun getPhone()
    fun getRealFirstName()
    fun getRealLastName()
    fun getRoles()
    fun getStatus()
    fun getTimezone()
    fun getTitle()
}

@Node(labels = ["User", "BaseNode"])
class User(
        about: String?,
        displayFirstName: String?,
        displayLastName: String?,
        email: String?,
        phone: String?,
        realFirstName: String?,
        realLastName: String?,
        roles: MutableList<Role>?,
        status: UserStatus?,
        timezone: String?,
        title: String?,
        @Relationship(type = "member", direction = Relationship.Direction.INCOMING)
        @JsonIgnore
        var membershipsGlobal: List<GlobalSecurityGroup> = listOf(),
        @Relationship(type = "member", direction = Relationship.Direction.INCOMING)
        @JsonIgnore
        var membershipsProject: List<ProjectSecurityGroup> = listOf(),
) : BaseNode() {

    @Relationship(type = "about")
    @JsonIgnore
    var aboutH: MutableList<StringProp> = mutableListOf()

    init {
        aboutH.add(StringProp(about))
    }

    var about = about
        set(value) {
            field = updateMember(
                    about,
                    aboutH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "displayFirstName")
    @JsonIgnore
    var displayFirstNameH: MutableList<StringProp> = mutableListOf()

    init {
        displayFirstNameH.add(StringProp(displayFirstName))
    }

    var displayFirstName = displayFirstName
        set(value) {
            field = updateMember(
                    displayFirstName,
                    displayFirstNameH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "displayLastName")
    @JsonIgnore
    var displayLastNameH: MutableList<StringProp> = mutableListOf()

    init {
        displayLastNameH.add(StringProp(displayLastName))
    }

    var displayLastName = displayLastName
        set(value) {
            field = updateMember(
                    displayLastName,
                    displayLastNameH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "email")
    @JsonIgnore
    var emailH: MutableList<EmailProp> = mutableListOf()

    init {
        emailH.add(EmailProp(email))
    }

    @Index
    var email = email
        set(value) {
            field = updateMember(
                    email,
                    emailH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }


    @Relationship(type = "phone")
    @JsonIgnore
    var phoneH: MutableList<StringProp> = mutableListOf()

    init {
        phoneH.add(StringProp(phone))
    }

    var phone = phone
        set(value) {
            field = updateMember(
                    phone,
                    phoneH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "realFirstName")
    @JsonIgnore
    var realFirstNameH: MutableList<StringProp> = mutableListOf()

    init {
        realFirstNameH.add(StringProp(realFirstName))
    }

    var realFirstName = realFirstName
        set(value) {
            field = updateMember(
                    realFirstName,
                    realFirstNameH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "realLastName")
    @JsonIgnore
    var realLastNameH: MutableList<StringProp> = mutableListOf()

    init {
        realLastNameH.add(StringProp(realLastName))
    }

    var realLastName = realLastName
        set(value) {
            field = updateMember(
                    realLastName,
                    realLastNameH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "role")
    @JsonIgnore
    var rolesH: MutableList<RoleProp> = mutableListOf()

    var roles = roles
        set(value) {
            if (field == null) field = mutableListOf()
            if (value == null) return
            val added = value.minus(field!!.toHashSet())
            val removed = roles?.minus(value)
            removed?.forEach f@{ removedRoleString ->
                val removedRole = removedRoleString as? Role ?: return@f
                // find each node in the history list and set the deletedAt prop
                rolesH.forEach f@{ roleNode ->
                    if (roleNode.deletedAt != null) return@f
                    if (roleNode.value.toString() == removedRole.name) {
                        val now = ZonedDateTime.now()
                        roleNode.deletedAt = now
                    }
                }
            }
            added.forEach f@{ newRoleString ->
                val newRole = newRoleString as? Role ?: return@f
                rolesH.add(RoleProp(newRole))
            }
            field = value
        }

    init {
        if (roles == null) this.roles = mutableListOf()
        roles?.forEach { rolesH.add(RoleProp(it)) }
    }

    @Relationship(type = "status")
    @JsonIgnore
    var statusH: MutableList<UserStatusProp> = mutableListOf()

    init {
        statusH.add(UserStatusProp(status))
    }

    var status = status
        set(value) {
            field = updateMember(
                    status,
                    statusH as MutableList<PropertyNode>,
                    value,
                    ::UserStatusProp
            ) ?: field
        }

    @Relationship(type = "timezone")
    @JsonIgnore
    var timezoneH: MutableList<StringProp> = mutableListOf()

    init {
        timezoneH.add(StringProp(timezone))
    }

    var timezone = timezone
        set(value) {
            field = updateMember(
                    timezone,
                    timezoneH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }

    @Relationship(type = "title")
    @JsonIgnore
    var titleH: MutableList<StringProp> = mutableListOf()

    init {
        titleH.add(StringProp(title))
    }

    var title = title
        set(value) {
            field = updateMember(
                    title,
                    titleH as MutableList<PropertyNode>,
                    value,
                    ::StringProp
            ) ?: field
        }
}
