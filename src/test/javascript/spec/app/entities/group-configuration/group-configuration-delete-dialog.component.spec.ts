/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { UniversignOedTestModule } from '../../../test.module';
import { GroupConfigurationDeleteDialogComponent } from 'app/entities/group-configuration/group-configuration-delete-dialog.component';
import { GroupConfigurationService } from 'app/entities/group-configuration/group-configuration.service';

describe('Component Tests', () => {
  describe('GroupConfiguration Management Delete Component', () => {
    let comp: GroupConfigurationDeleteDialogComponent;
    let fixture: ComponentFixture<GroupConfigurationDeleteDialogComponent>;
    let service: GroupConfigurationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [GroupConfigurationDeleteDialogComponent]
      })
        .overrideTemplate(GroupConfigurationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupConfigurationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupConfigurationService);
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
