import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IErrorRequest } from 'app/shared/model/error-request.model';
import { ErrorRequestService } from './error-request.service';

@Component({
  templateUrl: './error-request-delete-dialog.component.html',
})
export class ErrorRequestDeleteDialogComponent {
  errorRequest?: IErrorRequest;

  constructor(
    protected errorRequestService: ErrorRequestService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.errorRequestService.delete(id).subscribe(() => {
      this.eventManager.broadcast('errorRequestListModification');
      this.activeModal.close();
    });
  }
}
