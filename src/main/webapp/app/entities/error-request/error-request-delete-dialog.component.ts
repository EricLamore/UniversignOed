import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IErrorRequest } from 'app/shared/model/error-request.model';
import { ErrorRequestService } from './error-request.service';

@Component({
  selector: 'jhi-error-request-delete-dialog',
  templateUrl: './error-request-delete-dialog.component.html'
})
export class ErrorRequestDeleteDialogComponent {
  errorRequest: IErrorRequest;

  constructor(
    protected errorRequestService: ErrorRequestService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.errorRequestService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'errorRequestListModification',
        content: 'Deleted an errorRequest'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-error-request-delete-popup',
  template: ''
})
export class ErrorRequestDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ errorRequest }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ErrorRequestDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.errorRequest = errorRequest;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/error-request', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/error-request', { outlets: { popup: null } }]);
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
