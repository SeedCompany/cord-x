package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import java.time.ZonedDateTime

@Node
open class BaseNode : Entity() {

    fun <T> updateMember(
            field: T?,
            propH: MutableList<AnyProp>,
            value: T?,
            labels: Collection<String>,
            isUnique: Boolean = false,
    ): T? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
            if (isUnique) {
                it.deletedValue = it.value
                it.value = null
            }
        }
//        if (isUnique){
//            propH.add(UniqPropNode(value = value, labels = labels) as PropNode<T>)
//        } else {
//            propH.add(PropNode(value = value, labels = labels))
//        }
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

enum class PropLabel {
    Email,
    OrgName,
    Property,
    UserRole,
    UserStatus,
}