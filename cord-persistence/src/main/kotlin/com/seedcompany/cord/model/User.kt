package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime
import kotlin.reflect.KProperty

interface UserActiveReadOnly {
    //    fun getAbout()
//    fun getDisplayFirstName()
//    fun getDisplayLastName()
    fun getEmail()

    //    fun getPhone()
//    fun getRealFirstName()
//    fun getRealLastName()
    fun getRoles()
//    fun getStatus()
//    fun getTimezone()
//    fun getTitle()
}

@Node(labels = ["User", "BaseNode"])
class User(
//        about: String?,
//        displayFirstName: String?,
//        displayLastName: String?,
        email: String?,
//        phone: String?,
//        realFirstName: String?,
        realLastName: String?,
        roles: MutableList<Role>?,
//        status: UserStatus?,
//        timezone: String?,
//        title: String?,
//        @Relationship(type = "member", direction = Relationship.Direction.INCOMING)
//        @JsonIgnore
//        var membershipsGlobal: List<GlobalSecurityGroup> = listOf(),
//        @Relationship(type = "member", direction = Relationship.Direction.INCOMING)
//        @JsonIgnore
//        var membershipsProject: List<ProjectSecurityGroup> = listOf(),
) : BaseNode() {

//    @Relationship(type = "about")
//    @JsonIgnore
//    var _about: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _about.add(StringProp(about))
//    }
//
//    var about = about
//        set(value) {
//            if (about == value) return
//            _about.add(StringProp(value))
//            field = value
//        }
//
//    @Relationship(type = "displayFirstName")
//    @JsonIgnore
//    var _displayFirstName: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _displayFirstName.add(StringProp(displayFirstName))
//    }
//
//    var displayFirstName = displayFirstName
//        set(value) {
//            if (displayFirstName == value) return
//            _displayFirstName.add(StringProp(value))
//            field = value
//        }
//
//    @Relationship(type = "displayLastName")
//    @JsonIgnore
//    var _displayLastName: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _displayLastName.add(StringProp(displayLastName))
//    }
//
//    var displayLastName = displayLastName
//        set(value) {
//            if (displayLastName == value) return
//            _displayLastName.add(StringProp(value))
//            field = value
//        }

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


    //    @Relationship(type = "phone")
//    @JsonIgnore
//    var _phone: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _phone.add(StringProp(phone))
//    }
//
//    var phone = phone
//        set(value) {
//            if (phone == value) return
//            _phone.add(StringProp(value))
//            field = value
//        }
//
//    @Relationship(type = "realFirstName")
//    @JsonIgnore
//    var _realFirstName: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _realFirstName.add(StringProp(realFirstName))
//    }
//
//    var realFirstName = realFirstName
//        set(value) {
//            if (realFirstName == value) return
//            _realFirstName.add(StringProp(value))
//            field = value
//        }
//
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
//
//    @Relationship(type = "status")
//    @JsonIgnore
//    var _status: MutableList<UserStatusProp> = mutableListOf()
//
//    init {
//        _status.add(UserStatusProp(status))
//    }
//
//    var status = status
//        set(value) {
//            if (status == value) return
//            _status.add(UserStatusProp(value))
//            field = value
//        }
//
//    @Relationship(type = "timezone")
//    @JsonIgnore
//    var _timezone: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _timezone.add(StringProp(timezone))
//    }
//
//    var timezone = timezone
//        set(value) {
//            if (timezone == value) return
//            _timezone.add(StringProp(value))
//            field = value
//        }

//    @Relationship(type = "title")
//    @JsonIgnore
//    var _title: MutableList<StringProp> = mutableListOf()
//
//    init {
//        _title.add(StringProp(title))
//    }
//
//    var title = title
//        set(value) {
//            if (title == value) return
//            _title.add(StringProp(value))
//            field = value
//        }
}
