import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IErrorRequest, ErrorRequest } from 'app/shared/model/error-request.model';
import { ErrorRequestService } from './error-request.service';

@Component({
  selector: 'jhi-error-request-update',
  templateUrl: './error-request-update.component.html',
})
export class ErrorRequestUpdateComponent implements OnInit {
  isSaving = false;
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    comment: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    emailAddress: [null, [Validators.required]],
    birthDate: [],
  });

  constructor(protected errorRequestService: ErrorRequestService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ errorRequest }) => {
      this.updateForm(errorRequest);
    });
  }

  updateForm(errorRequest: IErrorRequest): void {
    this.editForm.patchValue({
      id: errorRequest.id,
      comment: errorRequest.comment,
      firstName: errorRequest.firstName,
      lastName: errorRequest.lastName,
      emailAddress: errorRequest.emailAddress,
      birthDate: errorRequest.birthDate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const errorRequest = this.createFromForm();
    if (errorRequest.id !== undefined) {
      this.subscribeToSaveResponse(this.errorRequestService.update(errorRequest));
    } else {
      this.subscribeToSaveResponse(this.errorRequestService.create(errorRequest));
    }
  }

  private createFromForm(): IErrorRequest {
    return {
      ...new ErrorRequest(),
      id: this.editForm.get(['id'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      emailAddress: this.editForm.get(['emailAddress'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IErrorRequest>>): void {
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
