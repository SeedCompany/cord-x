package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class User(
        var name: String,
): BaseNode() {
        @Relationship(type="name")
        var _name: MutableList<StringPropertyRelationship> = mutableListOf()

        init {
                val nameProp = StringProp(name)
                val nameRel = StringPropertyRelationship(nameProp)
                _name.add(nameRel)
        }

}
