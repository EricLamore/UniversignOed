/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { MapPropertiesUpdateComponent } from 'app/entities/map-properties/map-properties-update.component';
import { MapPropertiesService } from 'app/entities/map-properties/map-properties.service';
import { MapProperties } from 'app/shared/model/map-properties.model';

describe('Component Tests', () => {
  describe('MapProperties Management Update Component', () => {
    let comp: MapPropertiesUpdateComponent;
    let fixture: ComponentFixture<MapPropertiesUpdateComponent>;
    let service: MapPropertiesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [MapPropertiesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MapPropertiesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MapPropertiesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MapPropertiesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MapProperties('123');
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
        const entity = new MapProperties();
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
