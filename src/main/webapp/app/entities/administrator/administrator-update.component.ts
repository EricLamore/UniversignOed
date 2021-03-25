import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAdministrator, Administrator } from 'app/shared/model/administrator.model';
import { AdministratorService } from './administrator.service';
import { IAdminPermissions } from 'app/shared/model/admin-permissions.model';
import { AdminPermissionsService } from 'app/entities/admin-permissions/admin-permissions.service';
import { IAdminPreferences } from 'app/shared/model/admin-preferences.model';
import { AdminPreferencesService } from 'app/entities/admin-preferences/admin-preferences.service';

type SelectableEntity = IAdminPermissions | IAdminPreferences;

@Component({
  selector: 'jhi-administrator-update',
  templateUrl: './administrator-update.component.html',
})
export class AdministratorUpdateComponent implements OnInit {
  isSaving = false;
  permissions: IAdminPermissions[] = [];
  dashboardpreferences: IAdminPreferences[] = [];
  oedpreferences: IAdminPreferences[] = [];
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    emailAddress: [null, [Validators.required]],
    birthDate: [],
    role: [],
    password: [null, [Validators.required]],
    status: [],
    adminFunction: [],
    phone: [],
    mobilePhone: [],
    description: [],
    universignAdmin: [],
    activationKey: [],
    resetKey: [],
    resetDate: [],
    permissions: [],
    dashboardPreferences: [],
    oedPreferences: [],
  });

  constructor(
    protected administratorService: AdministratorService,
    protected adminPermissionsService: AdminPermissionsService,
    protected adminPreferencesService: AdminPreferencesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ administrator }) => {
      if (!administrator.id) {
        const today = moment().startOf('day');
        administrator.resetDate = today;
      }

      this.updateForm(administrator);

      this.adminPermissionsService
        .query({ filter: 'administrator-is-null' })
        .pipe(
          map((res: HttpResponse<IAdminPermissions[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAdminPermissions[]) => {
          if (!administrator.permissions || !administrator.permissions.id) {
            this.permissions = resBody;
          } else {
            this.adminPermissionsService
              .find(administrator.permissions.id)
              .pipe(
                map((subRes: HttpResponse<IAdminPermissions>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAdminPermissions[]) => (this.permissions = concatRes));
          }
        });

      this.adminPreferencesService
        .query({ filter: 'administrator-is-null' })
        .pipe(
          map((res: HttpResponse<IAdminPreferences[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAdminPreferences[]) => {
          if (!administrator.dashboardPreferences || !administrator.dashboardPreferences.id) {
            this.dashboardpreferences = resBody;
          } else {
            this.adminPreferencesService
              .find(administrator.dashboardPreferences.id)
              .pipe(
                map((subRes: HttpResponse<IAdminPreferences>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAdminPreferences[]) => (this.dashboardpreferences = concatRes));
          }
        });

      this.adminPreferencesService
        .query({ filter: 'administrator-is-null' })
        .pipe(
          map((res: HttpResponse<IAdminPreferences[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAdminPreferences[]) => {
          if (!administrator.oedPreferences || !administrator.oedPreferences.id) {
            this.oedpreferences = resBody;
          } else {
            this.adminPreferencesService
              .find(administrator.oedPreferences.id)
              .pipe(
                map((subRes: HttpResponse<IAdminPreferences>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAdminPreferences[]) => (this.oedpreferences = concatRes));
          }
        });
    });
  }

  updateForm(administrator: IAdministrator): void {
    this.editForm.patchValue({
      id: administrator.id,
      firstName: administrator.firstName,
      lastName: administrator.lastName,
      emailAddress: administrator.emailAddress,
      birthDate: administrator.birthDate,
      role: administrator.role,
      password: administrator.password,
      status: administrator.status,
      adminFunction: administrator.adminFunction,
      phone: administrator.phone,
      mobilePhone: administrator.mobilePhone,
      description: administrator.description,
      universignAdmin: administrator.universignAdmin,
      activationKey: administrator.activationKey,
      resetKey: administrator.resetKey,
      resetDate: administrator.resetDate ? administrator.resetDate.format(DATE_TIME_FORMAT) : null,
      permissions: administrator.permissions,
      dashboardPreferences: administrator.dashboardPreferences,
      oedPreferences: administrator.oedPreferences,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const administrator = this.createFromForm();
    if (administrator.id !== undefined) {
      this.subscribeToSaveResponse(this.administratorService.update(administrator));
    } else {
      this.subscribeToSaveResponse(this.administratorService.create(administrator));
    }
  }

  private createFromForm(): IAdministrator {
    return {
      ...new Administrator(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      emailAddress: this.editForm.get(['emailAddress'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value,
      role: this.editForm.get(['role'])!.value,
      password: this.editForm.get(['password'])!.value,
      status: this.editForm.get(['status'])!.value,
      adminFunction: this.editForm.get(['adminFunction'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      mobilePhone: this.editForm.get(['mobilePhone'])!.value,
      description: this.editForm.get(['description'])!.value,
      universignAdmin: this.editForm.get(['universignAdmin'])!.value,
      activationKey: this.editForm.get(['activationKey'])!.value,
      resetKey: this.editForm.get(['resetKey'])!.value,
      resetDate: this.editForm.get(['resetDate'])!.value ? moment(this.editForm.get(['resetDate'])!.value, DATE_TIME_FORMAT) : undefined,
      permissions: this.editForm.get(['permissions'])!.value,
      dashboardPreferences: this.editForm.get(['dashboardPreferences'])!.value,
      oedPreferences: this.editForm.get(['oedPreferences'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdministrator>>): void {
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
