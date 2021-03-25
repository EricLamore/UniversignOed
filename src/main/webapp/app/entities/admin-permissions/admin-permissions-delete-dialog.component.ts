import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminPermissions } from 'app/shared/model/admin-permissions.model';
import { AdminPermissionsService } from './admin-permissions.service';

@Component({
  templateUrl: './admin-permissions-delete-dialog.component.html',
})
export class AdminPermissionsDeleteDialogComponent {
  adminPermissions?: IAdminPermissions;

  constructor(
    protected adminPermissionsService: AdminPermissionsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.adminPermissionsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adminPermissionsListModification');
      this.activeModal.close();
    });
  }
}
