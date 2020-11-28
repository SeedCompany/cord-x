package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.neo4j.ogm.annotation.Index

@Node(labels = ["Email", "Property"])
class EmailProp(
        @Index(unique = true)
        var value: String? = null,
) : PropertyNode()
