package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node

interface IRole {
    fun name(): Role
    fun grants(): Map<PropName, Perm>
}

@Node(labels = ["Role", "Property"])
class RoleProp(
        var value: Role? = null,
) : PropertyNode()

enum class Role {
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

enum class GlobalRole {
    Administrator,
    ConsultantManager,
    Controller,
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

