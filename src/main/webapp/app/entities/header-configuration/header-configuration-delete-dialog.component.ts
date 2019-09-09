import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { HeaderConfigurationService } from './header-configuration.service';

@Component({
  selector: 'jhi-header-configuration-delete-dialog',
  templateUrl: './header-configuration-delete-dialog.component.html'
})
export class HeaderConfigurationDeleteDialogComponent {
  headerConfiguration: IHeaderConfiguration;

  constructor(
    protected headerConfigurationService: HeaderConfigurationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.headerConfigurationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'headerConfigurationListModification',
        content: 'Deleted an headerConfiguration'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-header-configuration-delete-popup',
  template: ''
})
export class HeaderConfigurationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ headerConfiguration }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(HeaderConfigurationDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.headerConfiguration = headerConfiguration;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/header-configuration', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/header-configuration', { outlets: { popup: null } }]);
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
