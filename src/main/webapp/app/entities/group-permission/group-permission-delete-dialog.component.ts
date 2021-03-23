import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from './group-permission.service';

@Component({
  templateUrl: './group-permission-delete-dialog.component.html',
})
export class GroupPermissionDeleteDialogComponent {
  groupPermission?: IGroupPermission;

  constructor(
    protected groupPermissionService: GroupPermissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.groupPermissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('groupPermissionListModification');
      this.activeModal.close();
    });
  }
}
