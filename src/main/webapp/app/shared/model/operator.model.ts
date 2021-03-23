import { Moment } from 'moment';
import { ICertifiedUser } from 'app/shared/model/certified-user.model';
import { IMapProperties } from 'app/shared/model/map-properties.model';
import { OpearatorStatus } from 'app/shared/model/enumerations/opearator-status.model';
import { SignatureStatus } from 'app/shared/model/enumerations/signature-status.model';
import { UniversignStatus } from 'app/shared/model/enumerations/universign-status.model';

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
  language?: string;
  groupId?: string;
  groupName?: string;
  signatureStatus?: SignatureStatus;
  isOED?: boolean;
  signatureSessionId?: string;
  signatureSessionURL?: string;
  universignStatus?: UniversignStatus;
  certified?: boolean;
  qualified?: boolean;
  previousFinalUniversignStatus?: SignatureStatus;
  firstName?: string;
  lastName?: string;
  emailAddress?: string;
  birthDate?: Moment;
  certifiedUser?: ICertifiedUser;
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
    public language?: string,
    public groupId?: string,
    public groupName?: string,
    public signatureStatus?: SignatureStatus,
    public isOED?: boolean,
    public signatureSessionId?: string,
    public signatureSessionURL?: string,
    public universignStatus?: UniversignStatus,
    public certified?: boolean,
    public qualified?: boolean,
    public previousFinalUniversignStatus?: SignatureStatus,
    public firstName?: string,
    public lastName?: string,
    public emailAddress?: string,
    public birthDate?: Moment,
    public certifiedUser?: ICertifiedUser,
    public metaDatas?: IMapProperties
  ) {
    this.updateAccessDate = this.updateAccessDate || false;
    this.updateTrainingDate = this.updateTrainingDate || false;
    this.hasSucceededMCQ = this.hasSucceededMCQ || false;
    this.updateQCMDate = this.updateQCMDate || false;
    this.isOED = this.isOED || false;
    this.certified = this.certified || false;
    this.qualified = this.qualified || false;
  }
}
