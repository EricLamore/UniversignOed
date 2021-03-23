export interface IMapProperties {
  id?: string;
  dummy?: string;
}

export class MapProperties implements IMapProperties {
  constructor(public id?: string, public dummy?: string) {}
}
