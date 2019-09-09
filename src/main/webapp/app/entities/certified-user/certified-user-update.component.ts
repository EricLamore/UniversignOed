import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICertifiedUser, CertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from './certified-user.service';

@Component({
  selector: 'jhi-certified-user-update',
  templateUrl: './certified-user-update.component.html'
})
export class CertifiedUserUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    firstname: [],
    lastname: [],
    email: [],
    phoneNumber: []
  });

  constructor(protected certifiedUserService: CertifiedUserService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ certifiedUser }) => {
      this.updateForm(certifiedUser);
    });
  }

  updateForm(certifiedUser: ICertifiedUser) {
    this.editForm.patchValue({
      id: certifiedUser.id,
      firstname: certifiedUser.firstname,
      lastname: certifiedUser.lastname,
      email: certifiedUser.email,
      phoneNumber: certifiedUser.phoneNumber
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      firstname: this.editForm.get(['firstname']).value,
      lastname: this.editForm.get(['lastname']).value,
      email: this.editForm.get(['email']).value,
      phoneNumber: this.editForm.get(['phoneNumber']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICertifiedUser>>) {
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
