/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { UniversignOedTestModule } from '../../../test.module';
import { ErrorRequestDeleteDialogComponent } from 'app/entities/error-request/error-request-delete-dialog.component';
import { ErrorRequestService } from 'app/entities/error-request/error-request.service';

describe('Component Tests', () => {
  describe('ErrorRequest Management Delete Component', () => {
    let comp: ErrorRequestDeleteDialogComponent;
    let fixture: ComponentFixture<ErrorRequestDeleteDialogComponent>;
    let service: ErrorRequestService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [ErrorRequestDeleteDialogComponent]
      })
        .overrideTemplate(ErrorRequestDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ErrorRequestDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ErrorRequestService);
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
