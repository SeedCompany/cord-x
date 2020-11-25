package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class User(
        about: String?,
        displayFirstName: String?,
        displayLastName: String?,
        email: String?,
) : BaseNode() {

    var about = about
        set(value) {
            if (about == value) return
            val aboutProp = StringProp(value)
            val aboutRel = StringPropertyRelationship(aboutProp)
            _about.add(aboutRel)
            field = value
        }

    var displayFirstName = displayFirstName
        set(value) {
            if (displayFirstName == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _displayFirstName.add(rel)
            field = value
        }

    var displayLastName = displayLastName
        set(value) {
            if (displayLastName == value) return
            val prop = StringProp(value)
            val rel = StringPropertyRelationship(prop)
            _displayLastName.add(rel)
            field = value
        }

    var email = email
        set(value) {
            if (email == value) return
            val prop = EmailProp(value)
            val rel = EmailPropertyRelationship(prop)
            _email.add(rel)
            field = value
        }

    @Relationship(type = "about")
    @JsonIgnore
    var _about: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val aboutProp = StringProp(about)
        val aboutRel = StringPropertyRelationship(aboutProp)
        _about.add(aboutRel)
    }

    @Relationship(type = "displayFirstName")
    @JsonIgnore
    var _displayFirstName: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(displayFirstName)
        val rel = StringPropertyRelationship(prop)
        _displayFirstName.add(rel)
    }

    @Relationship(type = "displayLastName")
    @JsonIgnore
    var _displayLastName: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val prop = StringProp(displayLastName)
        val rel = StringPropertyRelationship(prop)
        _displayLastName.add(rel)
    }

    @Relationship(type = "email")
    @JsonIgnore
    var _email: MutableList<EmailPropertyRelationship> = mutableListOf()

    init {
        val prop = EmailProp(email)
        val rel = EmailPropertyRelationship(prop)
        _email.add(rel)
    }
}
