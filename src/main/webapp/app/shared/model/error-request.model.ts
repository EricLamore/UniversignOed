import { Moment } from 'moment';

export interface IErrorRequest {
  id?: string;
  comment?: string;
  firstName?: string;
  lastName?: string;
  emailAddress?: string;
  birthDate?: Moment;
}

export class ErrorRequest implements IErrorRequest {
  constructor(
    public id?: string,
    public comment?: string,
    public firstName?: string,
    public lastName?: string,
    public emailAddress?: string,
    public birthDate?: Moment
  ) {}
}
