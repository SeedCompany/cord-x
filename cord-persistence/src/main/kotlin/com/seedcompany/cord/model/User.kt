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
        roles: List<Role> = listOf(),
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
    var _about: MutableList<StringProp> = mutableListOf()

    init {
        _about.add(StringProp(about))
    }

    var about = about
        set(value) {
            if (about == value) return
            _about.add(StringProp(value))
            field = value
        }

    @Relationship(type = "displayFirstName")
    @JsonIgnore
    var _displayFirstName: MutableList<StringProp> = mutableListOf()

    init {
        _displayFirstName.add(StringProp(displayFirstName))
    }

    var displayFirstName = displayFirstName
        set(value) {
            if (displayFirstName == value) return
            _displayFirstName.add(StringProp(value))
            field = value
        }

    @Relationship(type = "displayLastName")
    @JsonIgnore
    var _displayLastName: MutableList<StringProp> = mutableListOf()

    init {
        _displayLastName.add(StringProp(displayLastName))
    }

    var displayLastName = displayLastName
        set(value) {
            if (displayLastName == value) return
            _displayLastName.add(StringProp(value))
            field = value
        }

    @Relationship(type = "email")
    @JsonIgnore
    var _email: MutableList<EmailProp> = mutableListOf()

    init {
        _email.add(EmailProp(email))
    }

    @Index
    var email = email
        set(value) {
            if (email == value) return
            _email.add(EmailProp(value))
            field = value
        }


    @Relationship(type = "phone")
    @JsonIgnore
    var _phone: MutableList<StringProp> = mutableListOf()

    init {
        _phone.add(StringProp(phone))
    }

    var phone = phone
        set(value) {
            if (phone == value) return
            _phone.add(StringProp(value))
            field = value
        }

    @Relationship(type = "realFirstName")
    @JsonIgnore
    var _realFirstName: MutableList<StringProp> = mutableListOf()

    init {
        _realFirstName.add(StringProp(realFirstName))
    }

    var realFirstName = realFirstName
        set(value) {
            if (realFirstName == value) return
            _realFirstName.add(StringProp(value))
            field = value
        }

    @Relationship(type = "realLastName")
    @JsonIgnore
    var _realLastName: MutableList<StringProp> = mutableListOf()

    init {
        _realLastName.add(StringProp(realLastName))
    }

    var realLastName = realLastName
        set(value) {
            if (realLastName == value) return
            _realLastName.add(StringProp(value))
            field = value
        }

    @Relationship(type = "role")
    @JsonIgnore
    var _roles: MutableList<RoleProp> = mutableListOf()

    init {
        roles.forEach { _roles.add(RoleProp(it)) }
    }

    var roles = roles
        set(value) {
            if (field.equals(value)) return
            val added = value.minus(field) // added roles
            val removed = field.minus(value) // removed roles
            removed.forEach { _ ->
                _roles.forEach {
                    if (it.deletedAt == null) {
                        it.deletedAt = ZonedDateTime.now()
                        _roles.remove(it)
                    }
                }
            }
            added.forEach { _roles.add(RoleProp(it)) }
            field = value
        }

    @Relationship(type = "status")
    @JsonIgnore
    var _status: MutableList<UserStatusProp> = mutableListOf()

    init {
        _status.add(UserStatusProp(status))
    }

    var status = status
        set(value) {
            if (status == value) return
            _status.add(UserStatusProp(value))
            field = value
        }

    @Relationship(type = "timezone")
    @JsonIgnore
    var _timezone: MutableList<StringProp> = mutableListOf()

    init {
        _timezone.add(StringProp(timezone))
    }

    var timezone = timezone
        set(value) {
            if (timezone == value) return
            _timezone.add(StringProp(value))
            field = value
        }

    @Relationship(type = "title")
    @JsonIgnore
    var _title: MutableList<StringProp> = mutableListOf()

    init {
        _title.add(StringProp(title))
    }

    var title = title
        set(value) {
            if (title == value) return
            _title.add(StringProp(value))
            field = value
        }
}
