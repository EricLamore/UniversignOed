import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { CheckResultUpdateComponent } from 'app/entities/check-result/check-result-update.component';
import { CheckResultService } from 'app/entities/check-result/check-result.service';
import { CheckResult } from 'app/shared/model/check-result.model';

describe('Component Tests', () => {
  describe('CheckResult Management Update Component', () => {
    let comp: CheckResultUpdateComponent;
    let fixture: ComponentFixture<CheckResultUpdateComponent>;
    let service: CheckResultService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [CheckResultUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CheckResultUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CheckResultUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CheckResultService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CheckResult('123');
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
        const entity = new CheckResult();
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
