import { Moment } from 'moment';
import { GroupStatus } from 'app/shared/model/enumerations/group-status.model';

export interface IGroup {
  id?: string;
  name?: string;
  universignOrganizationId?: string;
  status?: GroupStatus;
  activateAdvanced?: boolean;
  universignOrganizationProfil?: string;
  createdAt?: Moment;
  lastUpdatedAt?: Moment;
}

export class Group implements IGroup {
  constructor(
    public id?: string,
    public name?: string,
    public universignOrganizationId?: string,
    public status?: GroupStatus,
    public activateAdvanced?: boolean,
    public universignOrganizationProfil?: string,
    public createdAt?: Moment,
    public lastUpdatedAt?: Moment
  ) {
    this.activateAdvanced = this.activateAdvanced || false;
  }
}
