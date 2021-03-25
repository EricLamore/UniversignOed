import { IAdminPreferences } from 'app/shared/model/admin-preferences.model';

export interface IColumn {
  id?: string;
  show?: boolean;
  name?: string;
  type?: string;
  adminPreferences?: IAdminPreferences;
}

export class Column implements IColumn {
  constructor(
    public id?: string,
    public show?: boolean,
    public name?: string,
    public type?: string,
    public adminPreferences?: IAdminPreferences
  ) {
    this.show = this.show || false;
  }
}
