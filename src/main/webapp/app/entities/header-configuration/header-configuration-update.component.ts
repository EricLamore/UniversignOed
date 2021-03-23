import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IHeaderConfiguration, HeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { HeaderConfigurationService } from './header-configuration.service';

@Component({
  selector: 'jhi-header-configuration-update',
  templateUrl: './header-configuration-update.component.html',
})
export class HeaderConfigurationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    logo: [],
    width: [],
    href: [],
  });

  constructor(
    protected headerConfigurationService: HeaderConfigurationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ headerConfiguration }) => {
      this.updateForm(headerConfiguration);
    });
  }

  updateForm(headerConfiguration: IHeaderConfiguration): void {
    this.editForm.patchValue({
      id: headerConfiguration.id,
      logo: headerConfiguration.logo,
      width: headerConfiguration.width,
      href: headerConfiguration.href,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const headerConfiguration = this.createFromForm();
    if (headerConfiguration.id !== undefined) {
      this.subscribeToSaveResponse(this.headerConfigurationService.update(headerConfiguration));
    } else {
      this.subscribeToSaveResponse(this.headerConfigurationService.create(headerConfiguration));
    }
  }

  private createFromForm(): IHeaderConfiguration {
    return {
      ...new HeaderConfiguration(),
      id: this.editForm.get(['id'])!.value,
      logo: this.editForm.get(['logo'])!.value,
      width: this.editForm.get(['width'])!.value,
      href: this.editForm.get(['href'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHeaderConfiguration>>): void {
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
