import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IColumn } from 'app/shared/model/column.model';
import { ColumnService } from './column.service';

@Component({
  templateUrl: './column-delete-dialog.component.html',
})
export class ColumnDeleteDialogComponent {
  column?: IColumn;

  constructor(protected columnService: ColumnService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.columnService.delete(id).subscribe(() => {
      this.eventManager.broadcast('columnListModification');
      this.activeModal.close();
    });
  }
}
