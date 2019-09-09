import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMapProperties, MapProperties } from 'app/shared/model/map-properties.model';
import { MapPropertiesService } from './map-properties.service';

@Component({
  selector: 'jhi-map-properties-update',
  templateUrl: './map-properties-update.component.html'
})
export class MapPropertiesUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    dummy: []
  });

  constructor(protected mapPropertiesService: MapPropertiesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mapProperties }) => {
      this.updateForm(mapProperties);
    });
  }

  updateForm(mapProperties: IMapProperties) {
    this.editForm.patchValue({
      id: mapProperties.id,
      dummy: mapProperties.dummy
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mapProperties = this.createFromForm();
    if (mapProperties.id !== undefined) {
      this.subscribeToSaveResponse(this.mapPropertiesService.update(mapProperties));
    } else {
      this.subscribeToSaveResponse(this.mapPropertiesService.create(mapProperties));
    }
  }

  private createFromForm(): IMapProperties {
    return {
      ...new MapProperties(),
      id: this.editForm.get(['id']).value,
      dummy: this.editForm.get(['dummy']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMapProperties>>) {
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
