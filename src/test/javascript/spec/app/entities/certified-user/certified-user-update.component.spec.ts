import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { CertifiedUserUpdateComponent } from 'app/entities/certified-user/certified-user-update.component';
import { CertifiedUserService } from 'app/entities/certified-user/certified-user.service';
import { CertifiedUser } from 'app/shared/model/certified-user.model';

describe('Component Tests', () => {
  describe('CertifiedUser Management Update Component', () => {
    let comp: CertifiedUserUpdateComponent;
    let fixture: ComponentFixture<CertifiedUserUpdateComponent>;
    let service: CertifiedUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [CertifiedUserUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CertifiedUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CertifiedUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CertifiedUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CertifiedUser('123');
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
        const entity = new CertifiedUser();
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
