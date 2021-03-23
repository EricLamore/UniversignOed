import { Moment } from 'moment';
import { IMapProperties } from 'app/shared/model/map-properties.model';
import { OpearatorStatus } from 'app/shared/model/enumerations/opearator-status.model';

export interface IOperator {
  id?: string;
  status?: OpearatorStatus;
  phoneNumber?: string;
  invitationDate?: Moment;
  invitationUrl?: string;
  updateAccessDate?: boolean;
  accessDate?: Moment;
  updateTrainingDate?: boolean;
  trainingDate?: Moment;
  hasSucceededMCQ?: boolean;
  updateQCMDate?: boolean;
  mcqDate?: Moment;
  trainingSessionId?: string;
  trainingSessionURL?: string;
  sessionId?: string;
  groupId?: string;
  metaDatas?: IMapProperties;
}

export class Operator implements IOperator {
  constructor(
    public id?: string,
    public status?: OpearatorStatus,
    public phoneNumber?: string,
    public invitationDate?: Moment,
    public invitationUrl?: string,
    public updateAccessDate?: boolean,
    public accessDate?: Moment,
    public updateTrainingDate?: boolean,
    public trainingDate?: Moment,
    public hasSucceededMCQ?: boolean,
    public updateQCMDate?: boolean,
    public mcqDate?: Moment,
    public trainingSessionId?: string,
    public trainingSessionURL?: string,
    public sessionId?: string,
    public groupId?: string,
    public metaDatas?: IMapProperties
  ) {
    this.updateAccessDate = this.updateAccessDate || false;
    this.updateTrainingDate = this.updateTrainingDate || false;
    this.hasSucceededMCQ = this.hasSucceededMCQ || false;
    this.updateQCMDate = this.updateQCMDate || false;
  }
}
