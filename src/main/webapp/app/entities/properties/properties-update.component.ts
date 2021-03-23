import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProperties, Properties } from 'app/shared/model/properties.model';
import { PropertiesService } from './properties.service';

@Component({
  selector: 'jhi-properties-update',
  templateUrl: './properties-update.component.html',
})
export class PropertiesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected propertiesService: PropertiesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ properties }) => {
      this.updateForm(properties);
    });
  }

  updateForm(properties: IProperties): void {
    this.editForm.patchValue({
      id: properties.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const properties = this.createFromForm();
    if (properties.id !== undefined) {
      this.subscribeToSaveResponse(this.propertiesService.update(properties));
    } else {
      this.subscribeToSaveResponse(this.propertiesService.create(properties));
    }
  }

  private createFromForm(): IProperties {
    return {
      ...new Properties(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProperties>>): void {
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
