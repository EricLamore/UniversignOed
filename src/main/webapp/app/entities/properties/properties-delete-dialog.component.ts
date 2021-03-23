import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProperties } from 'app/shared/model/properties.model';
import { PropertiesService } from './properties.service';

@Component({
  templateUrl: './properties-delete-dialog.component.html',
})
export class PropertiesDeleteDialogComponent {
  properties?: IProperties;

  constructor(
    protected propertiesService: PropertiesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.propertiesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('propertiesListModification');
      this.activeModal.close();
    });
  }
}
