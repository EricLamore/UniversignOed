import { Moment } from 'moment';

export interface IAdministrator {
  id?: string;
  firstName?: string;
  lastName?: string;
  emailAddress?: string;
  birthDate?: Moment;
}

export class Administrator implements IAdministrator {
  constructor(
    public id?: string,
    public firstName?: string,
    public lastName?: string,
    public emailAddress?: string,
    public birthDate?: Moment
  ) {}
}
