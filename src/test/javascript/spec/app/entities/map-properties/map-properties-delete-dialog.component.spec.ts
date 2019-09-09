/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { UniversignOedTestModule } from '../../../test.module';
import { MapPropertiesDeleteDialogComponent } from 'app/entities/map-properties/map-properties-delete-dialog.component';
import { MapPropertiesService } from 'app/entities/map-properties/map-properties.service';

describe('Component Tests', () => {
  describe('MapProperties Management Delete Component', () => {
    let comp: MapPropertiesDeleteDialogComponent;
    let fixture: ComponentFixture<MapPropertiesDeleteDialogComponent>;
    let service: MapPropertiesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [MapPropertiesDeleteDialogComponent]
      })
        .overrideTemplate(MapPropertiesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MapPropertiesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MapPropertiesService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete('123');
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith('123');
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
