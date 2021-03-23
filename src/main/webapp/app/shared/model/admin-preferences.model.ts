import { IColumn } from 'app/shared/model/column.model';

export interface IAdminPreferences {
  id?: string;
  filter?: string;
  columns?: IColumn[];
}

export class AdminPreferences implements IAdminPreferences {
  constructor(public id?: string, public filter?: string, public columns?: IColumn[]) {}
}
