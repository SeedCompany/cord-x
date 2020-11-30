package com.seedcompany.cord.common

import com.seedcompany.cord.model.Perm
import com.seedcompany.cord.model.PropName
import com.seedcompany.cord.model.Role
import com.seedcompany.cord.role.*

object AllRoles {
    fun grants(role: Role): Map<PropName, Perm>{
        when (role) {
            Role.Administrator -> return Administrator.grants()
            Role.BibleTranslationLiaison -> return BibleTranslationLiaison.grants()
            Role.Consultant -> return Consultant.grants()
            Role.ConsultantManager -> return ConsultantManager.grants()
            Role.Controller -> return Controller.grants()
            Role.Development -> return Development.grants()
            Role.ExecutiveDevelopmentRepresentative -> return ExecutiveDevelopmentRepresentative.grants()
            Role.ExecutiveLeadership -> return ExecutiveLeadership.grants()
            Role.FieldOperationsDirector -> return FieldOperationsDirector.grants()
            Role.FieldPartner -> return FieldPartner.grants()
            Role.FinancialAnalyst -> return FinancialAnalyst.grants()
            Role.Fundraising -> return Fundraising.grants()
            Role.Intern -> return Intern.grants()
            Role.LeadFinancialAnalyst -> return LeadFinancialAnalyst.grants()
            Role.Leadership -> return Leadership.grants()
            Role.Liason -> return Liason.grants()
            Role.Marketing -> return Marketing.grants()
            Role.Mentor -> return Mentor.grants()
            Role.OfficeOfThePresident -> return OfficeOfThePresident.grants()
            Role.ProjectManager -> return ProjectManager.grants()
            Role.RegionalCommunicationsCoordinator -> return RegionalCommunicationsCoordinator.grants()
            Role.RegionalDirector -> return RegionalDirector.grants()
            Role.StaffMember -> return StaffMember.grants()
            Role.SupportingProjectManager -> return SupportingProjectManager.grants()
            Role.Translator -> return Translator.grants()
            Role.Writer -> return Writer.grants()
        }
    }
}