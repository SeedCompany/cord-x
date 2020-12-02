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
        addressH.add(StringProp(value = address, labels = listOf("Property")))
    }

    var address = address
        set(value) {
            field = updateStringMember(
                    field = address,
                    propH = addressH,
                    value = value,
                    labels = listOf(PropLabel.Property.name)
            ) ?: field
        }

    @Relationship(type = "name")
    @JsonIgnore
    var nameH: MutableList<OrgNameProp> = mutableListOf()

    init {
        nameH.add(OrgNameProp(
                value = name,
                labels = listOf(PropLabel.OrgName.name, PropLabel.Property.name)
        ))
    }

    @Index(unique = true)
    var name = name
        set(value) {
            field = updateOrgNameMember(
                    field = name,
                    propH = nameH,
                    value = value,
                    labels = listOf(PropLabel.OrgName.name, PropLabel.Property.name),
            ) ?: field
        }
}