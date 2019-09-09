import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IGroup, Group } from 'app/shared/model/group.model';
import { GroupService } from './group.service';
import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';
import { GroupConfigurationService } from 'app/entities/group-configuration';
import { IGroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from 'app/entities/group-permission';

@Component({
  selector: 'jhi-group-update',
  templateUrl: './group-update.component.html'
})
export class GroupUpdateComponent implements OnInit {
  isSaving: boolean;

  groupconfigurations: IGroupConfiguration[];

  parents: IGroup[];

  permissions: IGroupPermission[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    universignOrganizationId: [],
    status: [],
    activateAdvanced: [],
    universignOrganizationProfil: [],
    groupConfiguration: [],
    parent: [],
    permissions: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected groupService: GroupService,
    protected groupConfigurationService: GroupConfigurationService,
    protected groupPermissionService: GroupPermissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ group }) => {
      this.updateForm(group);
    });
    this.groupConfigurationService
      .query({ filter: 'group-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IGroupConfiguration[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGroupConfiguration[]>) => response.body)
      )
      .subscribe(
        (res: IGroupConfiguration[]) => {
          if (!this.editForm.get('groupConfiguration').value || !this.editForm.get('groupConfiguration').value.id) {
            this.groupconfigurations = res;
          } else {
            this.groupConfigurationService
              .find(this.editForm.get('groupConfiguration').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IGroupConfiguration>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IGroupConfiguration>) => subResponse.body)
              )
              .subscribe(
                (subRes: IGroupConfiguration) => (this.groupconfigurations = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.groupService
      .query({ filter: 'group-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IGroup[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGroup[]>) => response.body)
      )
      .subscribe(
        (res: IGroup[]) => {
          if (!this.editForm.get('parent').value || !this.editForm.get('parent').value.id) {
            this.parents = res;
          } else {
            this.groupService
              .find(this.editForm.get('parent').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IGroup>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IGroup>) => subResponse.body)
              )
              .subscribe(
                (subRes: IGroup) => (this.parents = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.groupPermissionService
      .query({ filter: 'group-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IGroupPermission[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGroupPermission[]>) => response.body)
      )
      .subscribe(
        (res: IGroupPermission[]) => {
          if (!this.editForm.get('permissions').value || !this.editForm.get('permissions').value.id) {
            this.permissions = res;
          } else {
            this.groupPermissionService
              .find(this.editForm.get('permissions').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IGroupPermission>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IGroupPermission>) => subResponse.body)
              )
              .subscribe(
                (subRes: IGroupPermission) => (this.permissions = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(group: IGroup) {
    this.editForm.patchValue({
      id: group.id,
      name: group.name,
      universignOrganizationId: group.universignOrganizationId,
      status: group.status,
      activateAdvanced: group.activateAdvanced,
      universignOrganizationProfil: group.universignOrganizationProfil,
      groupConfiguration: group.groupConfiguration,
      parent: group.parent,
      permissions: group.permissions
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const group = this.createFromForm();
    if (group.id !== undefined) {
      this.subscribeToSaveResponse(this.groupService.update(group));
    } else {
      this.subscribeToSaveResponse(this.groupService.create(group));
    }
  }

  private createFromForm(): IGroup {
    return {
      ...new Group(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      universignOrganizationId: this.editForm.get(['universignOrganizationId']).value,
      status: this.editForm.get(['status']).value,
      activateAdvanced: this.editForm.get(['activateAdvanced']).value,
      universignOrganizationProfil: this.editForm.get(['universignOrganizationProfil']).value,
      groupConfiguration: this.editForm.get(['groupConfiguration']).value,
      parent: this.editForm.get(['parent']).value,
      permissions: this.editForm.get(['permissions']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroup>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackGroupConfigurationById(index: number, item: IGroupConfiguration) {
    return item.id;
  }

  trackGroupById(index: number, item: IGroup) {
    return item.id;
  }

  trackGroupPermissionById(index: number, item: IGroupPermission) {
    return item.id;
  }
}
