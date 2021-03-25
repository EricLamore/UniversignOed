import { Moment } from 'moment';
import { IGroupPermission } from 'app/shared/model/group-permission.model';
import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';
import { ILink } from 'app/shared/model/link.model';
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
  permissions?: IGroupPermission;
  groupConfiguration?: IGroupConfiguration;
  parent?: IGroup;
  administrators?: ILink[];
  operators?: ILink[];
  affiliatedGroups?: ILink[];
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
    public lastUpdatedAt?: Moment,
    public permissions?: IGroupPermission,
    public groupConfiguration?: IGroupConfiguration,
    public parent?: IGroup,
    public administrators?: ILink[],
    public operators?: ILink[],
    public affiliatedGroups?: ILink[]
  ) {
    this.activateAdvanced = this.activateAdvanced || false;
  }
}
