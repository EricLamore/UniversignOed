import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from './group-permission.service';

@Component({
  selector: 'jhi-group-permission-delete-dialog',
  templateUrl: './group-permission-delete-dialog.component.html'
})
export class GroupPermissionDeleteDialogComponent {
  groupPermission: IGroupPermission;

  constructor(
    protected groupPermissionService: GroupPermissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.groupPermissionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'groupPermissionListModification',
        content: 'Deleted an groupPermission'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-group-permission-delete-popup',
  template: ''
})
export class GroupPermissionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupPermission }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GroupPermissionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.groupPermission = groupPermission;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/group-permission', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/group-permission', { outlets: { popup: null } }]);
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
