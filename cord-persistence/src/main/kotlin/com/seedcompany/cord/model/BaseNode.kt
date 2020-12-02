package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import java.time.ZonedDateTime

@Node
open class BaseNode: Entity() {

    fun <T> updateMember(
            field: T?,
            propH: MutableList<PropertyNode>,
            value: T,
            propNodeFn: (T) -> PropertyNode,
            isUnique: Boolean = false,
    ): T? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
            // if (isUnique)
        }
        propH.add(propNodeFn(value))
        return value
    }

    fun <T> updateMember2(
            field: T?,
            propH: MutableList<TProp<T>>,
            value: T,
            labels: Collection<String>,
            isUnique: Boolean = false,
    ): T? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
             if (isUnique) {

             }
        }
        propH.add(TProp<T>(labels, value))
        return value
    }

}

enum class BaseNodeLabel {
    Budget,
    BudgetRecord,
    Ceremony,
    Directory,
    Education,
    Engagement,
    EthnologueLanguage,
    FieldRegion,
    FieldZone,
    File,
    FileNode,
    FileVersion,
    Film,
    FundingAccount,
    InternshipEngagement,
    Language,
    LanguageEngagement,
    LiteracyMaterial,
    Location,
    Organization,
    Partner,
    Partnership,
    Project,
    ProjectMember,
    Producible,
    Product,
    Song,
    Story,
    Unavailability,
    User,
}