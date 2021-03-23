import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICheckResult } from 'app/shared/model/check-result.model';
import { CheckResultService } from './check-result.service';

@Component({
  templateUrl: './check-result-delete-dialog.component.html',
})
export class CheckResultDeleteDialogComponent {
  checkResult?: ICheckResult;

  constructor(
    protected checkResultService: CheckResultService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.checkResultService.delete(id).subscribe(() => {
      this.eventManager.broadcast('checkResultListModification');
      this.activeModal.close();
    });
  }
}
