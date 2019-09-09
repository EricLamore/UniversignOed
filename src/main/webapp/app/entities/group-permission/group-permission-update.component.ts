import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGroupPermission, GroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from './group-permission.service';

@Component({
  selector: 'jhi-group-permission-update',
  templateUrl: './group-permission-update.component.html'
})
export class GroupPermissionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    moveOperatorsToUniversignOrganization: [],
    allowCustomization: [],
    allowAffiliatedGroup: [],
    allowAffiliatedCustomization: []
  });

  constructor(
    protected groupPermissionService: GroupPermissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ groupPermission }) => {
      this.updateForm(groupPermission);
    });
  }

  updateForm(groupPermission: IGroupPermission) {
    this.editForm.patchValue({
      id: groupPermission.id,
      moveOperatorsToUniversignOrganization: groupPermission.moveOperatorsToUniversignOrganization,
      allowCustomization: groupPermission.allowCustomization,
      allowAffiliatedGroup: groupPermission.allowAffiliatedGroup,
      allowAffiliatedCustomization: groupPermission.allowAffiliatedCustomization
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const groupPermission = this.createFromForm();
    if (groupPermission.id !== undefined) {
      this.subscribeToSaveResponse(this.groupPermissionService.update(groupPermission));
    } else {
      this.subscribeToSaveResponse(this.groupPermissionService.create(groupPermission));
    }
  }

  private createFromForm(): IGroupPermission {
    return {
      ...new GroupPermission(),
      id: this.editForm.get(['id']).value,
      moveOperatorsToUniversignOrganization: this.editForm.get(['moveOperatorsToUniversignOrganization']).value,
      allowCustomization: this.editForm.get(['allowCustomization']).value,
      allowAffiliatedGroup: this.editForm.get(['allowAffiliatedGroup']).value,
      allowAffiliatedCustomization: this.editForm.get(['allowAffiliatedCustomization']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupPermission>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
