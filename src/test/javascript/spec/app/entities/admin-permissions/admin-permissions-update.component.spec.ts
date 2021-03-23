import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { AdminPermissionsUpdateComponent } from 'app/entities/admin-permissions/admin-permissions-update.component';
import { AdminPermissionsService } from 'app/entities/admin-permissions/admin-permissions.service';
import { AdminPermissions } from 'app/shared/model/admin-permissions.model';

describe('Component Tests', () => {
  describe('AdminPermissions Management Update Component', () => {
    let comp: AdminPermissionsUpdateComponent;
    let fixture: ComponentFixture<AdminPermissionsUpdateComponent>;
    let service: AdminPermissionsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [AdminPermissionsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdminPermissionsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdminPermissionsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdminPermissionsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminPermissions('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AdminPermissions();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
