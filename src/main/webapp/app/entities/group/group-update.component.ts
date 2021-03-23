import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGroup, Group } from 'app/shared/model/group.model';
import { GroupService } from './group.service';

@Component({
  selector: 'jhi-group-update',
  templateUrl: './group-update.component.html',
})
export class GroupUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    universignOrganizationId: [],
    status: [],
    activateAdvanced: [],
    universignOrganizationProfil: [],
    createdAt: [],
    lastUpdatedAt: [],
  });

  constructor(protected groupService: GroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ group }) => {
      if (!group.id) {
        const today = moment().startOf('day');
        group.createdAt = today;
        group.lastUpdatedAt = today;
      }

      this.updateForm(group);
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
      createdAt: group.createdAt ? group.createdAt.format(DATE_TIME_FORMAT) : null,
      lastUpdatedAt: group.lastUpdatedAt ? group.lastUpdatedAt.format(DATE_TIME_FORMAT) : null,
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
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      lastUpdatedAt: this.editForm.get(['lastUpdatedAt'])!.value
        ? moment(this.editForm.get(['lastUpdatedAt'])!.value, DATE_TIME_FORMAT)
        : undefined,
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
}
