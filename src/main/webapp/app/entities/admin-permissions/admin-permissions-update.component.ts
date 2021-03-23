import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdminPermissions, AdminPermissions } from 'app/shared/model/admin-permissions.model';
import { AdminPermissionsService } from './admin-permissions.service';

@Component({
  selector: 'jhi-admin-permissions-update',
  templateUrl: './admin-permissions-update.component.html',
})
export class AdminPermissionsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    oedWrite: [],
    groupWrite: [],
    adminWrite: [],
  });

  constructor(
    protected adminPermissionsService: AdminPermissionsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminPermissions }) => {
      this.updateForm(adminPermissions);
    });
  }

  updateForm(adminPermissions: IAdminPermissions): void {
    this.editForm.patchValue({
      id: adminPermissions.id,
      oedWrite: adminPermissions.oedWrite,
      groupWrite: adminPermissions.groupWrite,
      adminWrite: adminPermissions.adminWrite,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adminPermissions = this.createFromForm();
    if (adminPermissions.id !== undefined) {
      this.subscribeToSaveResponse(this.adminPermissionsService.update(adminPermissions));
    } else {
      this.subscribeToSaveResponse(this.adminPermissionsService.create(adminPermissions));
    }
  }

  private createFromForm(): IAdminPermissions {
    return {
      ...new AdminPermissions(),
      id: this.editForm.get(['id'])!.value,
      oedWrite: this.editForm.get(['oedWrite'])!.value,
      groupWrite: this.editForm.get(['groupWrite'])!.value,
      adminWrite: this.editForm.get(['adminWrite'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminPermissions>>): void {
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
}
