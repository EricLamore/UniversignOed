import { IGroup } from 'app/shared/model/group.model';

export interface ILink {
  id?: string;
  group?: IGroup;
  group?: IGroup;
  group?: IGroup;
}

export class Link implements ILink {
  constructor(public id?: string, public group?: IGroup, public group?: IGroup, public group?: IGroup) {}
}
