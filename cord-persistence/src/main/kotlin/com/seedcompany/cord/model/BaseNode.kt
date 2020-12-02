package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import java.time.ZonedDateTime

@Node
open class BaseNode : Entity() {

    fun updateStringMember(
            field: String?,
            propH: MutableList<StringProp>,
            value: String?,
            labels: Collection<String>,
    ): String? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
        }
        propH.add(StringProp(value = value, labels = labels))
        return value
    }

    fun updateBooleanMember(
            field: Boolean?,
            propH: MutableList<BooleanProp>,
            value: Boolean?,
            labels: Collection<String>,
    ): Boolean? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
        }
        propH.add(BooleanProp(value = value, labels = labels))
        return value
    }

    fun updateNumberMember(
            field: Long?,
            propH: MutableList<NumberProp>,
            value: Long?,
            labels: Collection<String>,
    ): Long? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
        }
        propH.add(NumberProp(value = value, labels = labels))
        return value
    }

    fun updateRoleMember(
            field: Role?,
            propH: MutableList<RoleProp>,
            value: Role?,
            labels: Collection<String>,
    ): Role? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
        }
        propH.add(RoleProp(value = value, labels = labels))
        return value
    }

    fun updateEmailMember(
            field: String?,
            propH: MutableList<EmailProp>,
            value: String?,
            labels: Collection<String>,
    ): String? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedValue = it.value
            it.value = null
            it.deletedAt = now
        }
        propH.add(EmailProp(value = value, labels = labels))
        return value
    }

    fun updateUserStatusMember(
            field: UserStatus?,
            propH: MutableList<UserStatusProp>,
            value: UserStatus?,
            labels: Collection<String>,
    ): UserStatus? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedAt = now
        }
        propH.add(UserStatusProp(value = value, labels = labels))
        return value
    }

    fun updateOrgNameMember(
            field: String?,
            propH: MutableList<OrgNameProp>,
            value: String?,
            labels: Collection<String>,
    ): String? {
        if (field == value) return null
        propH.forEach f@{
            if (it.deletedAt != null) return@f
            val now = ZonedDateTime.now()
            it.deletedValue = it.value
            it.value = null
            it.deletedAt = now
        }
        propH.add(OrgNameProp(value = value, labels = labels))
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