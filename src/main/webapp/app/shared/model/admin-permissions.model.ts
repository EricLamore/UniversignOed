export interface IAdminPermissions {
  id?: string;
  oedWrite?: boolean;
  groupWrite?: boolean;
  adminWrite?: boolean;
}

export class AdminPermissions implements IAdminPermissions {
  constructor(public id?: string, public oedWrite?: boolean, public groupWrite?: boolean, public adminWrite?: boolean) {
    this.oedWrite = this.oedWrite || false;
    this.groupWrite = this.groupWrite || false;
    this.adminWrite = this.adminWrite || false;
  }
}
