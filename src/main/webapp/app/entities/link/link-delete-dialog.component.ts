import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILink } from 'app/shared/model/link.model';
import { LinkService } from './link.service';

@Component({
  selector: 'jhi-link-delete-dialog',
  templateUrl: './link-delete-dialog.component.html'
})
export class LinkDeleteDialogComponent {
  link: ILink;

  constructor(protected linkService: LinkService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.linkService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'linkListModification',
        content: 'Deleted an link'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-link-delete-popup',
  template: ''
})
export class LinkDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ link }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LinkDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.link = link;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/link', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/link', { outlets: { popup: null } }]);
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
