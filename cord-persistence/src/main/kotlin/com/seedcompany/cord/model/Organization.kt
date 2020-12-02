package com.seedcompany.cord.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.neo4j.ogm.annotation.Index
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

interface OrganizationActiveReadOnly {
    fun getName()
    fun getAddress()
}

@Node(labels = ["Organization", "BaseNode"])
class Organization(
        address: String?,
        name: String?,
//        @Relationship(type = "location", direction = Relationship.Direction.OUTGOING)
//        @JsonIgnore
//        var locations: MutableList<Location> = mutableListOf(),
) : BaseNode() {
    @Relationship(type = "address")
    @JsonIgnore
    var addressH: MutableList<StringProp> = mutableListOf()

    init {
        addressH.add(StringProp(address))
    }

    var address = address
        set(value) {
            field = updateMember(
                    field = address,
                    propH = addressH as MutableList<PropertyNode>,
                    value = value,
                    propNodeFn = ::StringProp
            ) ?: field
        }

    @Relationship(type = "name")
    @JsonIgnore
    var nameH: MutableList<OrgNameProp> = mutableListOf()

    init {
        nameH.add(OrgNameProp(name))
    }

    @Index(unique = true)
    var name = name
        set(value) {
            field = updateMember2(
                    field = name,
                    propH = nameH as MutableList<TProp<String>>,
                    value = value,
                    labels = listOf("OrgName", "Property"),
                    isUnique = true,
            ) ?: field
        }
}