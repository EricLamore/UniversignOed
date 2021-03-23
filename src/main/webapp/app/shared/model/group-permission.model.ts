export interface IGroupPermission {
  id?: string;
  moveOperatorsToUniversignOrganization?: boolean;
  allowCustomization?: boolean;
  allowAffiliatedGroup?: boolean;
  allowAffiliatedCustomization?: boolean;
}

export class GroupPermission implements IGroupPermission {
  constructor(
    public id?: string,
    public moveOperatorsToUniversignOrganization?: boolean,
    public allowCustomization?: boolean,
    public allowAffiliatedGroup?: boolean,
    public allowAffiliatedCustomization?: boolean
  ) {
    this.moveOperatorsToUniversignOrganization = this.moveOperatorsToUniversignOrganization || false;
    this.allowCustomization = this.allowCustomization || false;
    this.allowAffiliatedGroup = this.allowAffiliatedGroup || false;
    this.allowAffiliatedCustomization = this.allowAffiliatedCustomization || false;
  }
}
