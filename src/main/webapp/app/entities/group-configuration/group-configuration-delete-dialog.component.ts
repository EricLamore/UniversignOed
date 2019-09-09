import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';
import { GroupConfigurationService } from './group-configuration.service';

@Component({
  selector: 'jhi-group-configuration-delete-dialog',
  templateUrl: './group-configuration-delete-dialog.component.html'
})
export class GroupConfigurationDeleteDialogComponent {
  groupConfiguration: IGroupConfiguration;

  constructor(
    protected groupConfigurationService: GroupConfigurationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.groupConfigurationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'groupConfigurationListModification',
        content: 'Deleted an groupConfiguration'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-group-configuration-delete-popup',
  template: ''
})
export class GroupConfigurationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupConfiguration }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GroupConfigurationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.groupConfiguration = groupConfiguration;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/group-configuration', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/group-configuration', { outlets: { popup: null } }]);
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
