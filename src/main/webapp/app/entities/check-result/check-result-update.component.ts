import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICheckResult, CheckResult } from 'app/shared/model/check-result.model';
import { CheckResultService } from './check-result.service';

@Component({
  selector: 'jhi-check-result-update',
  templateUrl: './check-result-update.component.html',
})
export class CheckResultUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    checkErrorMessage: [],
    status: [],
    result: [],
  });

  constructor(protected checkResultService: CheckResultService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ checkResult }) => {
      this.updateForm(checkResult);
    });
  }

  updateForm(checkResult: ICheckResult): void {
    this.editForm.patchValue({
      id: checkResult.id,
      checkErrorMessage: checkResult.checkErrorMessage,
      status: checkResult.status,
      result: checkResult.result,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const checkResult = this.createFromForm();
    if (checkResult.id !== undefined) {
      this.subscribeToSaveResponse(this.checkResultService.update(checkResult));
    } else {
      this.subscribeToSaveResponse(this.checkResultService.create(checkResult));
    }
  }

  private createFromForm(): ICheckResult {
    return {
      ...new CheckResult(),
      id: this.editForm.get(['id'])!.value,
      checkErrorMessage: this.editForm.get(['checkErrorMessage'])!.value,
      status: this.editForm.get(['status'])!.value,
      result: this.editForm.get(['result'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICheckResult>>): void {
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
