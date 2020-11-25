package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class User(
        name: String,
) : BaseNode() {

    var name = name
        set(value) {
            if (name == value) return
            val nameProp = StringProp(value)
            val nameRel = StringPropertyRelationship(nameProp)
            _name.add(nameRel)
            field = value
        }

    @Relationship(type = "name")
    @JsonIgnore
    var _name: MutableList<StringPropertyRelationship> = mutableListOf()

    init {
        val nameProp = StringProp(name)
        val nameRel = StringPropertyRelationship(nameProp)
        _name.add(nameRel)
    }
}
