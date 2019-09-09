import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperator } from 'app/shared/model/operator.model';
import { OperatorService } from './operator.service';

@Component({
  selector: 'jhi-operator-delete-dialog',
  templateUrl: './operator-delete-dialog.component.html'
})
export class OperatorDeleteDialogComponent {
  operator: IOperator;

  constructor(protected operatorService: OperatorService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.operatorService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'operatorListModification',
        content: 'Deleted an operator'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-operator-delete-popup',
  template: ''
})
export class OperatorDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ operator }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OperatorDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.operator = operator;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/operator', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/operator', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
