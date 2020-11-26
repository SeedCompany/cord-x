package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

enum class FeRole {
    Administrator,
    BibleTranslationLiaison,
    Consultant,
    ConsultantManager,
    Controller,
    Development,
    ExecutiveDevelopmentRepresentative,
    ExecutiveLeadership,
    FieldOperationsDirector,
    FieldPartner,
    FinancialAnalyst,
    Fundraising,
    Intern,
    LeadFinancialAnalyst,
    Leadership,
    Liason,
    Marketing,
    Mentor,
    OfficeOfThePresident,
    ProjectManager,
    RegionalCommunicationsCoordinator,
    RegionalDirector,
    StaffMember,
    SupportingProjectManager,
    Translator,
    Writer,
}

enum class DbRole {
    AdministratorRole,
    BibleTranslationLiaisonRole,
    ConsultantRole,
    ConsultantManagerRole,
    ControllerRole,
    DevelopmentRole,
    ExecutiveDevelopmentRepresentativeRole,
    ExecutiveLeadershipRole,
    FieldOperationsDirectorRole,
    FieldPartnerRole,
    FinancialAnalystOnGlobalRole,
    FinancialAnalystOnProjectRole,
    FundraisingRole,
    InternRole,
    LeadFinancialAnalystRole,
    LeadershipRole,
    LiasonRole,
    MarketingRole,
    MentorRole,
    OfficeOfThePresidentRole,
    ProjectManagerGlobalRole,
    ProjectManagerOnProjectRole,
    RegionalCommunicationsCoordinatorRole,
    RegionalDirectorGlobalRole,
    RegionalDirectorOnProjectRole,
    StaffMemberRole,
    SupportingProjectManagerRole,
    TranslatorRole,
    WriterRole,
}

@Node(labels = ["Role", "Property"])
class RoleProp(
        var value: FeRole? = null,
) : PropertyNode()
