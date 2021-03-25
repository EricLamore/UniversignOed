import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IOperator, Operator } from 'app/shared/model/operator.model';
import { OperatorService } from './operator.service';
import { ICertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from 'app/entities/certified-user/certified-user.service';
import { IMapProperties } from 'app/shared/model/map-properties.model';
import { MapPropertiesService } from 'app/entities/map-properties/map-properties.service';

type SelectableEntity = ICertifiedUser | IMapProperties;

@Component({
  selector: 'jhi-operator-update',
  templateUrl: './operator-update.component.html',
})
export class OperatorUpdateComponent implements OnInit {
  isSaving = false;
  certifiedusers: ICertifiedUser[] = [];
  metadatas: IMapProperties[] = [];
  invitationDateDp: any;
  accessDateDp: any;
  trainingDateDp: any;
  mcqDateDp: any;
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    status: [null, [Validators.required]],
    phoneNumber: [],
    invitationDate: [],
    invitationUrl: [],
    updateAccessDate: [],
    accessDate: [],
    updateTrainingDate: [],
    trainingDate: [],
    hasSucceededMCQ: [],
    updateQCMDate: [],
    mcqDate: [],
    trainingSessionId: [],
    trainingSessionURL: [],
    sessionId: [],
    language: [],
    groupId: [],
    groupName: [],
    signatureStatus: [],
    isOED: [],
    signatureSessionId: [],
    signatureSessionURL: [],
    universignStatus: [],
    certified: [],
    qualified: [],
    previousFinalUniversignStatus: [],
    firstName: [],
    lastName: [],
    emailAddress: [],
    birthDate: [],
    certifiedUser: [],
    metaDatas: [],
  });

  constructor(
    protected operatorService: OperatorService,
    protected certifiedUserService: CertifiedUserService,
    protected mapPropertiesService: MapPropertiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operator }) => {
      this.updateForm(operator);

      this.certifiedUserService
        .query({ filter: 'operator-is-null' })
        .pipe(
          map((res: HttpResponse<ICertifiedUser[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICertifiedUser[]) => {
          if (!operator.certifiedUser || !operator.certifiedUser.id) {
            this.certifiedusers = resBody;
          } else {
            this.certifiedUserService
              .find(operator.certifiedUser.id)
              .pipe(
                map((subRes: HttpResponse<ICertifiedUser>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICertifiedUser[]) => (this.certifiedusers = concatRes));
          }
        });

      this.mapPropertiesService
        .query({ filter: 'operator-is-null' })
        .pipe(
          map((res: HttpResponse<IMapProperties[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMapProperties[]) => {
          if (!operator.metaDatas || !operator.metaDatas.id) {
            this.metadatas = resBody;
          } else {
            this.mapPropertiesService
              .find(operator.metaDatas.id)
              .pipe(
                map((subRes: HttpResponse<IMapProperties>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMapProperties[]) => (this.metadatas = concatRes));
          }
        });
    });
  }

  updateForm(operator: IOperator): void {
    this.editForm.patchValue({
      id: operator.id,
      status: operator.status,
      phoneNumber: operator.phoneNumber,
      invitationDate: operator.invitationDate,
      invitationUrl: operator.invitationUrl,
      updateAccessDate: operator.updateAccessDate,
      accessDate: operator.accessDate,
      updateTrainingDate: operator.updateTrainingDate,
      trainingDate: operator.trainingDate,
      hasSucceededMCQ: operator.hasSucceededMCQ,
      updateQCMDate: operator.updateQCMDate,
      mcqDate: operator.mcqDate,
      trainingSessionId: operator.trainingSessionId,
      trainingSessionURL: operator.trainingSessionURL,
      sessionId: operator.sessionId,
      language: operator.language,
      groupId: operator.groupId,
      groupName: operator.groupName,
      signatureStatus: operator.signatureStatus,
      isOED: operator.isOED,
      signatureSessionId: operator.signatureSessionId,
      signatureSessionURL: operator.signatureSessionURL,
      universignStatus: operator.universignStatus,
      certified: operator.certified,
      qualified: operator.qualified,
      previousFinalUniversignStatus: operator.previousFinalUniversignStatus,
      firstName: operator.firstName,
      lastName: operator.lastName,
      emailAddress: operator.emailAddress,
      birthDate: operator.birthDate,
      certifiedUser: operator.certifiedUser,
      metaDatas: operator.metaDatas,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operator = this.createFromForm();
    if (operator.id !== undefined) {
      this.subscribeToSaveResponse(this.operatorService.update(operator));
    } else {
      this.subscribeToSaveResponse(this.operatorService.create(operator));
    }
  }

  private createFromForm(): IOperator {
    return {
      ...new Operator(),
      id: this.editForm.get(['id'])!.value,
      status: this.editForm.get(['status'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      invitationDate: this.editForm.get(['invitationDate'])!.value,
      invitationUrl: this.editForm.get(['invitationUrl'])!.value,
      updateAccessDate: this.editForm.get(['updateAccessDate'])!.value,
      accessDate: this.editForm.get(['accessDate'])!.value,
      updateTrainingDate: this.editForm.get(['updateTrainingDate'])!.value,
      trainingDate: this.editForm.get(['trainingDate'])!.value,
      hasSucceededMCQ: this.editForm.get(['hasSucceededMCQ'])!.value,
      updateQCMDate: this.editForm.get(['updateQCMDate'])!.value,
      mcqDate: this.editForm.get(['mcqDate'])!.value,
      trainingSessionId: this.editForm.get(['trainingSessionId'])!.value,
      trainingSessionURL: this.editForm.get(['trainingSessionURL'])!.value,
      sessionId: this.editForm.get(['sessionId'])!.value,
      language: this.editForm.get(['language'])!.value,
      groupId: this.editForm.get(['groupId'])!.value,
      groupName: this.editForm.get(['groupName'])!.value,
      signatureStatus: this.editForm.get(['signatureStatus'])!.value,
      isOED: this.editForm.get(['isOED'])!.value,
      signatureSessionId: this.editForm.get(['signatureSessionId'])!.value,
      signatureSessionURL: this.editForm.get(['signatureSessionURL'])!.value,
      universignStatus: this.editForm.get(['universignStatus'])!.value,
      certified: this.editForm.get(['certified'])!.value,
      qualified: this.editForm.get(['qualified'])!.value,
      previousFinalUniversignStatus: this.editForm.get(['previousFinalUniversignStatus'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      emailAddress: this.editForm.get(['emailAddress'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value,
      certifiedUser: this.editForm.get(['certifiedUser'])!.value,
      metaDatas: this.editForm.get(['metaDatas'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperator>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
