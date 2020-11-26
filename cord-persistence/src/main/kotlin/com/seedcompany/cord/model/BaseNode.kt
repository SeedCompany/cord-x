package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node

@Node
open class BaseNode: Entity()

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