/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { UniversignOedTestModule } from '../../../test.module';
import { HeaderConfigurationDeleteDialogComponent } from 'app/entities/header-configuration/header-configuration-delete-dialog.component';
import { HeaderConfigurationService } from 'app/entities/header-configuration/header-configuration.service';

describe('Component Tests', () => {
  describe('HeaderConfiguration Management Delete Component', () => {
    let comp: HeaderConfigurationDeleteDialogComponent;
    let fixture: ComponentFixture<HeaderConfigurationDeleteDialogComponent>;
    let service: HeaderConfigurationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [HeaderConfigurationDeleteDialogComponent]
      })
        .overrideTemplate(HeaderConfigurationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HeaderConfigurationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HeaderConfigurationService);
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
