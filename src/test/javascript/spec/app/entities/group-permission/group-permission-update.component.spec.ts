import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { GroupPermissionUpdateComponent } from 'app/entities/group-permission/group-permission-update.component';
import { GroupPermissionService } from 'app/entities/group-permission/group-permission.service';
import { GroupPermission } from 'app/shared/model/group-permission.model';

describe('Component Tests', () => {
  describe('GroupPermission Management Update Component', () => {
    let comp: GroupPermissionUpdateComponent;
    let fixture: ComponentFixture<GroupPermissionUpdateComponent>;
    let service: GroupPermissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [GroupPermissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GroupPermissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GroupPermissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupPermissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GroupPermission('123');
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
        const entity = new GroupPermission();
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
