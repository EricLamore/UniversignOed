import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { GroupConfigurationUpdateComponent } from 'app/entities/group-configuration/group-configuration-update.component';
import { GroupConfigurationService } from 'app/entities/group-configuration/group-configuration.service';
import { GroupConfiguration } from 'app/shared/model/group-configuration.model';

describe('Component Tests', () => {
  describe('GroupConfiguration Management Update Component', () => {
    let comp: GroupConfigurationUpdateComponent;
    let fixture: ComponentFixture<GroupConfigurationUpdateComponent>;
    let service: GroupConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [GroupConfigurationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GroupConfigurationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GroupConfigurationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupConfigurationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GroupConfiguration('123');
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
        const entity = new GroupConfiguration();
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
