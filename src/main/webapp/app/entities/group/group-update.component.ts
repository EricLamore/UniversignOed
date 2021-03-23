import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IGroup, Group } from 'app/shared/model/group.model';
import { GroupService } from './group.service';
import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';
import { GroupConfigurationService } from 'app/entities/group-configuration/group-configuration.service';
import { IGroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from 'app/entities/group-permission/group-permission.service';

type SelectableEntity = IGroupConfiguration | IGroup | IGroupPermission;

@Component({
  selector: 'jhi-group-update',
  templateUrl: './group-update.component.html',
})
export class GroupUpdateComponent implements OnInit {
  isSaving = false;
  groupconfigurations: IGroupConfiguration[] = [];
  parents: IGroup[] = [];
  permissions: IGroupPermission[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    universignOrganizationId: [],
    status: [],
    activateAdvanced: [],
    universignOrganizationProfil: [],
    groupConfiguration: [],
    parent: [],
    permissions: [],
  });

  constructor(
    protected groupService: GroupService,
    protected groupConfigurationService: GroupConfigurationService,
    protected groupPermissionService: GroupPermissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ group }) => {
      this.updateForm(group);

      this.groupConfigurationService
        .query({ filter: 'group-is-null' })
        .pipe(
          map((res: HttpResponse<IGroupConfiguration[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGroupConfiguration[]) => {
          if (!group.groupConfiguration || !group.groupConfiguration.id) {
            this.groupconfigurations = resBody;
          } else {
            this.groupConfigurationService
              .find(group.groupConfiguration.id)
              .pipe(
                map((subRes: HttpResponse<IGroupConfiguration>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGroupConfiguration[]) => (this.groupconfigurations = concatRes));
          }
        });

      this.groupService
        .query({ filter: 'group-is-null' })
        .pipe(
          map((res: HttpResponse<IGroup[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGroup[]) => {
          if (!group.parent || !group.parent.id) {
            this.parents = resBody;
          } else {
            this.groupService
              .find(group.parent.id)
              .pipe(
                map((subRes: HttpResponse<IGroup>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGroup[]) => (this.parents = concatRes));
          }
        });

      this.groupPermissionService
        .query({ filter: 'group-is-null' })
        .pipe(
          map((res: HttpResponse<IGroupPermission[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGroupPermission[]) => {
          if (!group.permissions || !group.permissions.id) {
            this.permissions = resBody;
          } else {
            this.groupPermissionService
              .find(group.permissions.id)
              .pipe(
                map((subRes: HttpResponse<IGroupPermission>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGroupPermission[]) => (this.permissions = concatRes));
          }
        });
    });
  }

  updateForm(group: IGroup): void {
    this.editForm.patchValue({
      id: group.id,
      name: group.name,
      universignOrganizationId: group.universignOrganizationId,
      status: group.status,
      activateAdvanced: group.activateAdvanced,
      universignOrganizationProfil: group.universignOrganizationProfil,
      groupConfiguration: group.groupConfiguration,
      parent: group.parent,
      permissions: group.permissions,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      universignOrganizationId: this.editForm.get(['universignOrganizationId'])!.value,
      status: this.editForm.get(['status'])!.value,
      activateAdvanced: this.editForm.get(['activateAdvanced'])!.value,
      universignOrganizationProfil: this.editForm.get(['universignOrganizationProfil'])!.value,
      groupConfiguration: this.editForm.get(['groupConfiguration'])!.value,
      parent: this.editForm.get(['parent'])!.value,
      permissions: this.editForm.get(['permissions'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroup>>): void {
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
