import { Moment } from 'moment';
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
    public resetDate?: Moment
  ) {
    this.universignAdmin = this.universignAdmin || false;
  }
}
