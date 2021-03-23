import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminPermissions } from 'app/shared/model/admin-permissions.model';

@Component({
  selector: 'jhi-admin-permissions-detail',
  templateUrl: './admin-permissions-detail.component.html',
})
export class AdminPermissionsDetailComponent implements OnInit {
  adminPermissions: IAdminPermissions | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adminPermissions }) => (this.adminPermissions = adminPermissions));
  }

  previousState(): void {
    window.history.back();
  }
}
