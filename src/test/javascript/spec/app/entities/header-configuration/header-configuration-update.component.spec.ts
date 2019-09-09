/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { HeaderConfigurationUpdateComponent } from 'app/entities/header-configuration/header-configuration-update.component';
import { HeaderConfigurationService } from 'app/entities/header-configuration/header-configuration.service';
import { HeaderConfiguration } from 'app/shared/model/header-configuration.model';

describe('Component Tests', () => {
  describe('HeaderConfiguration Management Update Component', () => {
    let comp: HeaderConfigurationUpdateComponent;
    let fixture: ComponentFixture<HeaderConfigurationUpdateComponent>;
    let service: HeaderConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [HeaderConfigurationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(HeaderConfigurationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HeaderConfigurationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HeaderConfigurationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HeaderConfiguration('123');
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
        const entity = new HeaderConfiguration();
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
