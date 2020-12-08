package com.seedcompany.cord.common

import com.seedcompany.cord.model.Perm
import com.seedcompany.cord.model.Power
import com.seedcompany.cord.model.PropName
import com.seedcompany.cord.model.Role
import com.seedcompany.cord.role.*

object AllRoles {
    fun powers(role: Role): List<Power>{
        when (role) {
            Role.Administrator -> return Administrator.powers()
            Role.Anonymous -> return Anonymous.powers()
            Role.BibleTranslationLiaison -> return BibleTranslationLiaison.powers()
            Role.Consultant -> return Consultant.powers()
            Role.ConsultantManager -> return ConsultantManager.powers()
            Role.Controller -> return Controller.powers()
            Role.CordUser -> return CordUser.powers()
            Role.Development -> return Development.powers()
            Role.ExecutiveDevelopmentRepresentative -> return ExecutiveDevelopmentRepresentative.powers()
            Role.ExecutiveLeadership -> return ExecutiveLeadership.powers()
            Role.FieldOperationsDirector -> return FieldOperationsDirector.powers()
            Role.FieldPartner -> return FieldPartner.powers()
            Role.FinancialAnalyst -> return FinancialAnalyst.powers()
            Role.Fundraising -> return Fundraising.powers()
            Role.Intern -> return Intern.powers()
            Role.LeadFinancialAnalyst -> return LeadFinancialAnalyst.powers()
            Role.Leadership -> return Leadership.powers()
            Role.Liason -> return Liason.powers()
            Role.Marketing -> return Marketing.powers()
            Role.Mentor -> return Mentor.powers()
            Role.OfficeOfThePresident -> return OfficeOfThePresident.powers()
            Role.ProjectManager -> return ProjectManager.powers()
            Role.RegionalCommunicationsCoordinator -> return RegionalCommunicationsCoordinator.powers()
            Role.RegionalDirector -> return RegionalDirector.powers()
            Role.StaffMember -> return StaffMember.powers()
            Role.SupportingProjectManager -> return SupportingProjectManager.powers()
            Role.Translator -> return Translator.powers()
            Role.Writer -> return Writer.powers()
        }
    }
    fun grants(role: Role): Map<PropName, Perm>{
        when (role) {
            Role.Administrator -> return Administrator.grants()
            Role.Anonymous -> return Anonymous.grants()
            Role.BibleTranslationLiaison -> return BibleTranslationLiaison.grants()
            Role.Consultant -> return Consultant.grants()
            Role.ConsultantManager -> return ConsultantManager.grants()
            Role.Controller -> return Controller.grants()
            Role.CordUser -> return CordUser.grants()
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