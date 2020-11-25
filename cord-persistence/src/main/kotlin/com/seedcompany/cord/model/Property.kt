package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node

open class PropertyNode () : Entity()

@Node
class StringProp (
        var value: String?
) : PropertyNode()

@Node
class BooleanProp (
        var value: Boolean?
) : PropertyNode()

@Node
class NumberProp (
        var value: Long?
) : PropertyNode()
