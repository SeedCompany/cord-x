package com.seedcompany.cord.model

import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

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

@Node(labels = ["Role", "Property"])
class RoleProp(
        var value: Role? = null,
) : PropertyNode()

@RelationshipProperties
class RolePropertyRelationship(
        @TargetNode var toNode: RoleProp
) : PropertyRelationship()