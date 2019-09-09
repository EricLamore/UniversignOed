import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from './certified-user.service';

@Component({
  selector: 'jhi-certified-user-delete-dialog',
  templateUrl: './certified-user-delete-dialog.component.html'
})
export class CertifiedUserDeleteDialogComponent {
  certifiedUser: ICertifiedUser;

  constructor(
    protected certifiedUserService: CertifiedUserService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.certifiedUserService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'certifiedUserListModification',
        content: 'Deleted an certifiedUser'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-certified-user-delete-popup',
  template: ''
})
export class CertifiedUserDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ certifiedUser }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CertifiedUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.certifiedUser = certifiedUser;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/certified-user', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/certified-user', { outlets: { popup: null } }]);
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
