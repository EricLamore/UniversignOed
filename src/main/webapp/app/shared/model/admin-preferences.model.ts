export interface IAdminPreferences {
  id?: string;
  filter?: string;
}

export class AdminPreferences implements IAdminPreferences {
  constructor(public id?: string, public filter?: string) {}
}
