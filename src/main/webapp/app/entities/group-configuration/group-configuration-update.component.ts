import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IGroupConfiguration, GroupConfiguration } from 'app/shared/model/group-configuration.model';
import { GroupConfigurationService } from './group-configuration.service';
import { IHeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { HeaderConfigurationService } from 'app/entities/header-configuration/header-configuration.service';
import { IProperties } from 'app/shared/model/properties.model';
import { PropertiesService } from 'app/entities/properties/properties.service';
import { IMapProperties } from 'app/shared/model/map-properties.model';
import { MapPropertiesService } from 'app/entities/map-properties/map-properties.service';

type SelectableEntity = IHeaderConfiguration | IProperties | IMapProperties;

@Component({
  selector: 'jhi-group-configuration-update',
  templateUrl: './group-configuration-update.component.html',
})
export class GroupConfigurationUpdateComponent implements OnInit {
  isSaving = false;
  headers: IHeaderConfiguration[] = [];
  i18ns: IProperties[] = [];
  metadatas: IProperties[] = [];
  languages: IMapProperties[] = [];

  editForm = this.fb.group({
    id: [],
    header: [],
    i18n: [],
    metaDatas: [],
    languages: [],
  });

  constructor(
    protected groupConfigurationService: GroupConfigurationService,
    protected headerConfigurationService: HeaderConfigurationService,
    protected propertiesService: PropertiesService,
    protected mapPropertiesService: MapPropertiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupConfiguration }) => {
      this.updateForm(groupConfiguration);

      this.headerConfigurationService
        .query({ filter: 'groupconfiguration-is-null' })
        .pipe(
          map((res: HttpResponse<IHeaderConfiguration[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IHeaderConfiguration[]) => {
          if (!groupConfiguration.header || !groupConfiguration.header.id) {
            this.headers = resBody;
          } else {
            this.headerConfigurationService
              .find(groupConfiguration.header.id)
              .pipe(
                map((subRes: HttpResponse<IHeaderConfiguration>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IHeaderConfiguration[]) => (this.headers = concatRes));
          }
        });

      this.propertiesService
        .query({ filter: 'groupconfiguration-is-null' })
        .pipe(
          map((res: HttpResponse<IProperties[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProperties[]) => {
          if (!groupConfiguration.i18n || !groupConfiguration.i18n.id) {
            this.i18ns = resBody;
          } else {
            this.propertiesService
              .find(groupConfiguration.i18n.id)
              .pipe(
                map((subRes: HttpResponse<IProperties>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProperties[]) => (this.i18ns = concatRes));
          }
        });

      this.propertiesService
        .query({ filter: 'groupconfiguration-is-null' })
        .pipe(
          map((res: HttpResponse<IProperties[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProperties[]) => {
          if (!groupConfiguration.metaDatas || !groupConfiguration.metaDatas.id) {
            this.metadatas = resBody;
          } else {
            this.propertiesService
              .find(groupConfiguration.metaDatas.id)
              .pipe(
                map((subRes: HttpResponse<IProperties>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProperties[]) => (this.metadatas = concatRes));
          }
        });

      this.mapPropertiesService
        .query({ filter: 'groupconfiguration-is-null' })
        .pipe(
          map((res: HttpResponse<IMapProperties[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMapProperties[]) => {
          if (!groupConfiguration.languages || !groupConfiguration.languages.id) {
            this.languages = resBody;
          } else {
            this.mapPropertiesService
              .find(groupConfiguration.languages.id)
              .pipe(
                map((subRes: HttpResponse<IMapProperties>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMapProperties[]) => (this.languages = concatRes));
          }
        });
    });
  }

  updateForm(groupConfiguration: IGroupConfiguration): void {
    this.editForm.patchValue({
      id: groupConfiguration.id,
      header: groupConfiguration.header,
      i18n: groupConfiguration.i18n,
      metaDatas: groupConfiguration.metaDatas,
      languages: groupConfiguration.languages,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const groupConfiguration = this.createFromForm();
    if (groupConfiguration.id !== undefined) {
      this.subscribeToSaveResponse(this.groupConfigurationService.update(groupConfiguration));
    } else {
      this.subscribeToSaveResponse(this.groupConfigurationService.create(groupConfiguration));
    }
  }

  private createFromForm(): IGroupConfiguration {
    return {
      ...new GroupConfiguration(),
      id: this.editForm.get(['id'])!.value,
      header: this.editForm.get(['header'])!.value,
      i18n: this.editForm.get(['i18n'])!.value,
      metaDatas: this.editForm.get(['metaDatas'])!.value,
      languages: this.editForm.get(['languages'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupConfiguration>>): void {
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
