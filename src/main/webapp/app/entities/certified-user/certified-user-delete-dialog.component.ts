import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from './certified-user.service';

@Component({
  templateUrl: './certified-user-delete-dialog.component.html',
})
export class CertifiedUserDeleteDialogComponent {
  certifiedUser?: ICertifiedUser;

  constructor(
    protected certifiedUserService: CertifiedUserService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.certifiedUserService.delete(id).subscribe(() => {
      this.eventManager.broadcast('certifiedUserListModification');
      this.activeModal.close();
    });
  }
}
