import { Status } from 'app/shared/model/enumerations/status.model';
import { CheckStatus } from 'app/shared/model/enumerations/check-status.model';

export interface ICheckResult {
  id?: string;
  checkErrorMessage?: string;
  status?: Status;
  result?: CheckStatus;
}

export class CheckResult implements ICheckResult {
  constructor(public id?: string, public checkErrorMessage?: string, public status?: Status, public result?: CheckStatus) {}
}
