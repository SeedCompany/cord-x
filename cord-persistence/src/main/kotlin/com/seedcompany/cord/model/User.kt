package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

enum class UserStatus {
    Active,
    Disabled,
}

interface UserActiveReadOnly {
    fun getAbout()
    fun getDisplayFirstName()
    fun getDisplayLastName()
    fun getEmail()
    fun getPassword()
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
        about: String? = null,
        displayFirstName: String?,
        displayLastName: String?,
        email: String?,
        password: String? = null,
        phone: String? = null,
        realFirstName: String? = null,
        realLastName: String? = null,
        roles: MutableList<Role>? = null,
        status: UserStatus? = null,
        timezone: String? = null,
        title: String? = null,
        @Relationship(type = "member", direction = Relationship.Direction.INCOMING)
        @JsonIgnore
        var membershipsGlobal: List<GlobalSecurityGroup> = listOf(),
        @Relationship(type = "member", direction = Relationship.Direction.INCOMING)
        @JsonIgnore
        var membershipsProject: List<ProjectSecurityGroup> = listOf(),
) : BaseNode() {

    // create user from existing user but with a password hash
    constructor(
            user: User,
            passwordHash: String,
    ) : this(
            about = user.about,
            displayFirstName = user.displayFirstName,
            displayLastName = user.displayLastName,
            email = user.email,
            password = passwordHash,
            phone = user.phone,
            realFirstName = user.realFirstName,
            realLastName = user.realLastName,
            roles = user.roles,
            status = user.status,
            timezone = user.timezone,
            title = user.title,
    )

    @Relationship(type = "about")
    @JsonIgnore
    var aboutH: MutableList<StringProp> = mutableListOf()

    init {
        aboutH.add(StringProp(value = about, labels = listOf(PropLabel.Property.name)))
    }

    var about = about
        set(value) {
            field = updateStringMember(
                    about,
                    aboutH,
                    value,
                    labels = listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "displayFirstName")
    @JsonIgnore
    var displayFirstNameH: MutableList<StringProp> = mutableListOf()

    init {
        displayFirstNameH.add(StringProp(value = displayFirstName, labels = listOf(PropLabel.Property.name)))
    }

    var displayFirstName = displayFirstName
        set(value) {
            field = updateStringMember(
                    displayFirstName,
                    displayFirstNameH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "displayLastName")
    @JsonIgnore
    var displayLastNameH: MutableList<StringProp> = mutableListOf()

    init {
        displayLastNameH.add(StringProp(value = displayLastName, labels = listOf(PropLabel.Property.name)))
    }

    var displayLastName = displayLastName
        set(value) {
            field = updateStringMember(
                    displayLastName,
                    displayLastNameH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "email")
    @JsonIgnore
    var emailH: MutableList<EmailProp> = mutableListOf()

    init {
        emailH.add(EmailProp(value = email, labels = listOf(PropLabel.Property.name)))
    }

    @Index(unique = true)
    var email = email
        set(value) {
            field = updateEmailMember(
                    email,
                    emailH,
                    value,
                    listOf(PropLabel.Email.name, PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "password")
    @JsonIgnore
    var passwordH: MutableList<StringProp> = mutableListOf()

    init {
        passwordH.add(StringProp(value = password, labels = listOf(PropLabel.Property.name)))
    }

    var password = password
        set(value) {
            field = updateStringMember(
                    password,
                    passwordH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "phone")
    @JsonIgnore
    var phoneH: MutableList<StringProp> = mutableListOf()

    init {
        phoneH.add(StringProp(value = phone, labels = listOf(PropLabel.Property.name)))
    }

    var phone = phone
        set(value) {
            field = updateStringMember(
                    phone,
                    phoneH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "realFirstName")
    @JsonIgnore
    var realFirstNameH: MutableList<StringProp> = mutableListOf()

    init {
        realFirstNameH.add(StringProp(value = realFirstName, labels = listOf(PropLabel.Property.name)))
    }

    var realFirstName = realFirstName
        set(value) {
            field = updateStringMember(
                    realFirstName,
                    realFirstNameH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "realLastName")
    @JsonIgnore
    var realLastNameH: MutableList<StringProp> = mutableListOf()

    init {
        realLastNameH.add(StringProp(value = realLastName, labels = listOf(PropLabel.Property.name)))
    }

    var realLastName = realLastName
        set(value) {
            field = updateStringMember(
                    realLastName,
                    realLastNameH,
                    value,
                    listOf(PropLabel.Property.name)
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
            removed?.forEach a@{ removedRoleString ->
                val removedRole = removedRoleString as? Role ?: return@a
                // find each node in the history list and set the deletedAt prop
                rolesH.forEach b@{ roleNode ->
                    if (roleNode.deletedAt != null) return@b
                    if (roleNode.value.toString() == removedRole.name) {
                        val now = ZonedDateTime.now()
                        roleNode.deletedAt = now
                    }
                }
            }
            added.forEach c@{ newRoleString ->
                val newRole = newRoleString as? Role ?: return@c
                rolesH.add(RoleProp(value = newRole, labels = listOf(PropLabel.UserRole.name, PropLabel.Property.name)))
            }
            field = value
        }

    init {
        if (roles == null) this.roles = mutableListOf()
        roles?.forEach { rolesH.add(RoleProp(value = it, labels = listOf(PropLabel.UserRole.name, PropLabel.Property.name))) }
    }

    @Relationship(type = "status")
    @JsonIgnore
    var statusH: MutableList<UserStatusProp> = mutableListOf()

    init {
        statusH.add(UserStatusProp(value = status, labels = listOf(PropLabel.Property.name)))
    }

    var status = status
        set(value) {
            field = updateUserStatusMember(
                    status,
                    statusH,
                    value,
                    listOf(PropLabel.UserStatus.name, PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "timezone")
    @JsonIgnore
    var timezoneH: MutableList<StringProp> = mutableListOf()

    init {
        timezoneH.add(StringProp(value = timezone, labels = listOf(PropLabel.Property.name)))
    }

    var timezone = timezone
        set(value) {
            field = updateStringMember(
                    timezone,
                    timezoneH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "title")
    @JsonIgnore
    var titleH: MutableList<StringProp> = mutableListOf()

    init {
        titleH.add(StringProp(value = title, labels = listOf(PropLabel.Property.name)))
    }

    var title = title
        set(value) {
            field = updateStringMember(
                    title,
                    titleH,
                    value,
                    listOf(PropLabel.Property.name)
            ) ?: field
        }
}
