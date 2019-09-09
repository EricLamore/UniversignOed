import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapProperties } from 'app/shared/model/map-properties.model';
import { MapPropertiesService } from './map-properties.service';

@Component({
  selector: 'jhi-map-properties-delete-dialog',
  templateUrl: './map-properties-delete-dialog.component.html'
})
export class MapPropertiesDeleteDialogComponent {
  mapProperties: IMapProperties;

  constructor(
    protected mapPropertiesService: MapPropertiesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: string) {
    this.mapPropertiesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mapPropertiesListModification',
        content: 'Deleted an mapProperties'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-map-properties-delete-popup',
  template: ''
})
export class MapPropertiesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mapProperties }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MapPropertiesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mapProperties = mapProperties;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/map-properties', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/map-properties', { outlets: { popup: null } }]);
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
