import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IColumn, Column } from 'app/shared/model/column.model';
import { ColumnService } from './column.service';
import { IAdminPreferences } from 'app/shared/model/admin-preferences.model';
import { AdminPreferencesService } from 'app/entities/admin-preferences/admin-preferences.service';

@Component({
  selector: 'jhi-column-update',
  templateUrl: './column-update.component.html',
})
export class ColumnUpdateComponent implements OnInit {
  isSaving = false;
  adminpreferences: IAdminPreferences[] = [];

  editForm = this.fb.group({
    id: [],
    show: [],
    name: [],
    type: [],
    adminPreferences: [],
  });

  constructor(
    protected columnService: ColumnService,
    protected adminPreferencesService: AdminPreferencesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ column }) => {
      this.updateForm(column);

      this.adminPreferencesService.query().subscribe((res: HttpResponse<IAdminPreferences[]>) => (this.adminpreferences = res.body || []));
    });
  }

  updateForm(column: IColumn): void {
    this.editForm.patchValue({
      id: column.id,
      show: column.show,
      name: column.name,
      type: column.type,
      adminPreferences: column.adminPreferences,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const column = this.createFromForm();
    if (column.id !== undefined) {
      this.subscribeToSaveResponse(this.columnService.update(column));
    } else {
      this.subscribeToSaveResponse(this.columnService.create(column));
    }
  }

  private createFromForm(): IColumn {
    return {
      ...new Column(),
      id: this.editForm.get(['id'])!.value,
      show: this.editForm.get(['show'])!.value,
      name: this.editForm.get(['name'])!.value,
      type: this.editForm.get(['type'])!.value,
      adminPreferences: this.editForm.get(['adminPreferences'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IColumn>>): void {
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

  trackById(index: number, item: IAdminPreferences): any {
    return item.id;
  }
}
