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
  administratorStatus?: AdministratorStatus;
  adminFonction?: string;
  phone?: string;
  mobilePhone?: string;
  description?: string;
  universignAdmin?: boolean;
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
    public administratorStatus?: AdministratorStatus,
    public adminFonction?: string,
    public phone?: string,
    public mobilePhone?: string,
    public description?: string,
    public universignAdmin?: boolean
  ) {
    this.universignAdmin = this.universignAdmin || false;
  }
}
