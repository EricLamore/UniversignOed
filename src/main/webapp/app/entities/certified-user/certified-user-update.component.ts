import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICertifiedUser, CertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from './certified-user.service';

@Component({
  selector: 'jhi-certified-user-update',
  templateUrl: './certified-user-update.component.html',
})
export class CertifiedUserUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstname: [],
    lastname: [],
    email: [],
    phoneNumber: [],
  });

  constructor(protected certifiedUserService: CertifiedUserService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certifiedUser }) => {
      this.updateForm(certifiedUser);
    });
  }

  updateForm(certifiedUser: ICertifiedUser): void {
    this.editForm.patchValue({
      id: certifiedUser.id,
      firstname: certifiedUser.firstname,
      lastname: certifiedUser.lastname,
      email: certifiedUser.email,
      phoneNumber: certifiedUser.phoneNumber,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const certifiedUser = this.createFromForm();
    if (certifiedUser.id !== undefined) {
      this.subscribeToSaveResponse(this.certifiedUserService.update(certifiedUser));
    } else {
      this.subscribeToSaveResponse(this.certifiedUserService.create(certifiedUser));
    }
  }

  private createFromForm(): ICertifiedUser {
    return {
      ...new CertifiedUser(),
      id: this.editForm.get(['id'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      lastname: this.editForm.get(['lastname'])!.value,
      email: this.editForm.get(['email'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICertifiedUser>>): void {
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
