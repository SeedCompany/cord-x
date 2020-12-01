package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node

interface IRole {
    fun name(): Role
    fun grants(): Map<PropName, Perm>
}

enum class Role {
    Administrator,
    Anonymous,
    BibleTranslationLiaison,
    Consultant,
    ConsultantManager,
    Controller,
    CordUser,
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

// must be a subset of Role
enum class GlobalRole {
    Administrator,
    Anonymous,
    ConsultantManager,
    Controller,
    CordUser,
    FinancialAnalyst,
    Fundraising,
    Leadership,
    Marketing,
    Mentor,
    ProjectManager,
    RegionalCommunicationsCoordinator,
    StaffMember,
    Translator,
}

// must be a subset of Role
enum class ProjectRole {
    Administrator,
    Consultant,
    FinancialAnalyst,
    Intern,
    Liason,
    Mentor,
    ProjectManager,
    RegionalCommunicationsCoordinator,
    RegionalDirector,
    Translator,
}

