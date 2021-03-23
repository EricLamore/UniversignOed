import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';
import { IGroupPermission } from 'app/shared/model/group-permission.model';
import { ILink } from 'app/shared/model/link.model';
import { GroupStatus } from 'app/shared/model/enumerations/group-status.model';

export interface IGroup {
  id?: string;
  name?: string;
  universignOrganizationId?: string;
  status?: GroupStatus;
  activateAdvanced?: boolean;
  universignOrganizationProfil?: string;
  groupConfiguration?: IGroupConfiguration;
  parent?: IGroup;
  permissions?: IGroupPermission;
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
    public groupConfiguration?: IGroupConfiguration,
    public parent?: IGroup,
    public permissions?: IGroupPermission,
    public administrators?: ILink[],
    public operators?: ILink[],
    public affiliatedGroups?: ILink[]
  ) {
    this.activateAdvanced = this.activateAdvanced || false;
  }
}
