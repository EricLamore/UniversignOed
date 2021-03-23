import { Moment } from 'moment';
import { IAdminPermissions } from 'app/shared/model/admin-permissions.model';
import { IAdminPreferences } from 'app/shared/model/admin-preferences.model';
import { Role } from 'app/shared/model/enumerations/role.model';
import { AdministratorStatus } from 'app/shared/model/enumerations/administrator-status.model';

export interface IAdministrator {
  id?: string;
  firstName?: string;
  lastName?: string;
  emailAddress?: string;
  birthDate?: Moment;
  role?: Role;
  password?: string;
  status?: AdministratorStatus;
  adminFunction?: string;
  phone?: string;
  mobilePhone?: string;
  description?: string;
  universignAdmin?: boolean;
  activationKey?: string;
  resetKey?: string;
  resetDate?: Moment;
  permissions?: IAdminPermissions;
  dashboardPreferences?: IAdminPreferences;
  oedPreferences?: IAdminPreferences;
}

export class Administrator implements IAdministrator {
  constructor(
    public id?: string,
    public firstName?: string,
    public lastName?: string,
    public emailAddress?: string,
    public birthDate?: Moment,
    public role?: Role,
    public password?: string,
    public status?: AdministratorStatus,
    public adminFunction?: string,
    public phone?: string,
    public mobilePhone?: string,
    public description?: string,
    public universignAdmin?: boolean,
    public activationKey?: string,
    public resetKey?: string,
    public resetDate?: Moment,
    public permissions?: IAdminPermissions,
    public dashboardPreferences?: IAdminPreferences,
    public oedPreferences?: IAdminPreferences
  ) {
    this.universignAdmin = this.universignAdmin || false;
  }
}
