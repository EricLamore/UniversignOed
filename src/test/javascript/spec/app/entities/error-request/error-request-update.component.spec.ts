/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { ErrorRequestUpdateComponent } from 'app/entities/error-request/error-request-update.component';
import { ErrorRequestService } from 'app/entities/error-request/error-request.service';
import { ErrorRequest } from 'app/shared/model/error-request.model';

describe('Component Tests', () => {
  describe('ErrorRequest Management Update Component', () => {
    let comp: ErrorRequestUpdateComponent;
    let fixture: ComponentFixture<ErrorRequestUpdateComponent>;
    let service: ErrorRequestService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [ErrorRequestUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ErrorRequestUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ErrorRequestUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ErrorRequestService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ErrorRequest('123');
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
        const entity = new ErrorRequest();
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
