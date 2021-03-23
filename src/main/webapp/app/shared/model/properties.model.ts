export interface IProperties {
  id?: string;
}

export class Properties implements IProperties {
  constructor(public id?: string) {}
}
