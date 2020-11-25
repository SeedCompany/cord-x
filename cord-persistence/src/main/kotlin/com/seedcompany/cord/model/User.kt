package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.ZonedDateTime

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
) : BaseNode() {

    @Relationship(type = "about")
    @JsonIgnore
    var _about: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val aboutProp = StringProp(about)
        val aboutRel = StringPropertyRelationship(aboutProp)
        _about.add(aboutRel)
    }

    var about = about
        set(value) {
            if (about == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _about.add(rel)
            field = value
        }

    @Relationship(type = "displayFirstName")
    @JsonIgnore
    var _displayFirstName: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(displayFirstName)
        val rel = StringPropertyRelationship(prop)
        _displayFirstName.add(rel)
    }

    var displayFirstName = displayFirstName
        set(value) {
            if (displayFirstName == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _displayFirstName.add(rel)
            field = value
        }

    @Relationship(type = "displayLastName")
    @JsonIgnore
    var _displayLastName: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(displayLastName)
        val rel = StringPropertyRelationship(prop)
        _displayLastName.add(rel)
    }

    var displayLastName = displayLastName
        set(value) {
            if (displayLastName == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _displayLastName.add(rel)
            field = value
        }

    @Relationship(type = "email")
    @JsonIgnore
    var _email: MutableList<EmailPropertyRelationship> = mutableListOf()

    init {
        val prop = EmailProp(email)
        val rel = EmailPropertyRelationship(prop)
        _email.add(rel)
    }

    var email = email
        set(value) {
            if (email == value) return
            val prop = EmailProp(value)
            val rel = EmailPropertyRelationship(prop)
            _email.add(rel)
            field = value
        }

    @Relationship(type = "phone")
    @JsonIgnore
    var _phone: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(phone)
        val rel = StringPropertyRelationship(prop)
        _phone.add(rel)
    }

    var phone = phone
        set(value) {
            if (phone == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _phone.add(rel)
            field = value
        }

    @Relationship(type = "realFirstName")
    @JsonIgnore
    var _realFirstName: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(realFirstName)
        val rel = StringPropertyRelationship(prop)
        _realFirstName.add(rel)
    }

    var realFirstName = realFirstName
        set(value) {
            if (realFirstName == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _realFirstName.add(rel)
            field = value
        }

    @Relationship(type = "realLastName")
    @JsonIgnore
    var _realLastName: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(realLastName)
        val rel = StringPropertyRelationship(prop)
        _realLastName.add(rel)
    }

    var realLastName = realLastName
        set(value) {
            if (realLastName == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _realLastName.add(rel)
            field = value
        }

    @Relationship(type = "role")
    @JsonIgnore
    var _roles: MutableList<RolePropertyRelationship> = mutableListOf()

    init {
        roles?.forEach {
            val prop = RoleProp(it)
            val rel = RolePropertyRelationship(prop)
            _roles.add(rel)
        }
    }

    var roles = roles
        set(value) {

            if (field.equals(value)) return
            if (value == null) return

            val added = value.minus(field) // added roles
            val removed = field.minus(value) // removed roles

            removed.forEach{ role ->
                _roles.forEach {
                    if (it.deletedAt == null) {
                        if (it.toNode.value == role) {
                            it.toNode.deletedAt = ZonedDateTime.now()
                            it.deletedAt = ZonedDateTime.now()
                            _roles.remove(it)
                        }
                    }
                }
            }

            added.forEach {
                val prop = RoleProp(it)
                val rel = RolePropertyRelationship(prop)
                _roles.add(rel)
            }

            field = value
        }

    @Relationship(type = "status")
    @JsonIgnore
    var _status: MutableList<UserStatusPropertyRelationship> = mutableListOf()

    init {
        val prop = UserStatusProp(status)
        val rel = UserStatusPropertyRelationship(prop)
        _status.add(rel)
    }

    var status = status
        set(value) {
            if (status == value) return
            val prop = UserStatusProp(value)
            val rel = UserStatusPropertyRelationship(prop)
            _status.add(rel)
            field = value
        }

    @Relationship(type = "timezone")
    @JsonIgnore
    var _timezone: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(timezone)
        val rel = StringPropertyRelationship(prop)
        _timezone.add(rel)
    }

    var timezone = timezone
        set(value) {
            if (timezone == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _timezone.add(rel)
            field = value
        }

    @Relationship(type = "title")
    @JsonIgnore
    var _title: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(title)
        val rel = StringPropertyRelationship(prop)
        _title.add(rel)
    }

    var title = title
        set(value) {
            if (title == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _title.add(rel)
            field = value
        }
}
