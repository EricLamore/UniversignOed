import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { IAdministrator, Administrator } from 'app/shared/model/administrator.model';
import { AdministratorService } from './administrator.service';

@Component({
  selector: 'jhi-administrator-update',
  templateUrl: './administrator-update.component.html'
})
export class AdministratorUpdateComponent implements OnInit {
  isSaving: boolean;
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required]],
    lastName: [null, [Validators.required]],
    emailAddress: [null, [Validators.required]],
    birthDate: [],
    role: [],
    password: [null, [Validators.required]],
    administratorStatus: [],
    adminFonction: [],
    phone: [],
    mobilePhone: [],
    description: [],
    universignAdmin: []
  });

  constructor(protected administratorService: AdministratorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ administrator }) => {
      this.updateForm(administrator);
    });
  }

  updateForm(administrator: IAdministrator) {
    this.editForm.patchValue({
      id: administrator.id,
      firstName: administrator.firstName,
      lastName: administrator.lastName,
      emailAddress: administrator.emailAddress,
      birthDate: administrator.birthDate,
      role: administrator.role,
      password: administrator.password,
      administratorStatus: administrator.administratorStatus,
      adminFonction: administrator.adminFonction,
      phone: administrator.phone,
      mobilePhone: administrator.mobilePhone,
      description: administrator.description,
      universignAdmin: administrator.universignAdmin
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const administrator = this.createFromForm();
    if (administrator.id !== undefined) {
      this.subscribeToSaveResponse(this.administratorService.update(administrator));
    } else {
      this.subscribeToSaveResponse(this.administratorService.create(administrator));
    }
  }

  private createFromForm(): IAdministrator {
    return {
      ...new Administrator(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      emailAddress: this.editForm.get(['emailAddress']).value,
      birthDate: this.editForm.get(['birthDate']).value,
      role: this.editForm.get(['role']).value,
      password: this.editForm.get(['password']).value,
      administratorStatus: this.editForm.get(['administratorStatus']).value,
      adminFonction: this.editForm.get(['adminFonction']).value,
      phone: this.editForm.get(['phone']).value,
      mobilePhone: this.editForm.get(['mobilePhone']).value,
      description: this.editForm.get(['description']).value,
      universignAdmin: this.editForm.get(['universignAdmin']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdministrator>>) {
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
