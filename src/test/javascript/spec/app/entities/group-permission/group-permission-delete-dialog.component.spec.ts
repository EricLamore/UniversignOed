/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { UniversignOedTestModule } from '../../../test.module';
import { GroupPermissionDeleteDialogComponent } from 'app/entities/group-permission/group-permission-delete-dialog.component';
import { GroupPermissionService } from 'app/entities/group-permission/group-permission.service';

describe('Component Tests', () => {
  describe('GroupPermission Management Delete Component', () => {
    let comp: GroupPermissionDeleteDialogComponent;
    let fixture: ComponentFixture<GroupPermissionDeleteDialogComponent>;
    let service: GroupPermissionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [GroupPermissionDeleteDialogComponent]
      })
        .overrideTemplate(GroupPermissionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupPermissionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupPermissionService);
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
