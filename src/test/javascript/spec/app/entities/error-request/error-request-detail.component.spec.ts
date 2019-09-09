/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { ErrorRequestDetailComponent } from 'app/entities/error-request/error-request-detail.component';
import { ErrorRequest } from 'app/shared/model/error-request.model';

describe('Component Tests', () => {
  describe('ErrorRequest Management Detail Component', () => {
    let comp: ErrorRequestDetailComponent;
    let fixture: ComponentFixture<ErrorRequestDetailComponent>;
    const route = ({ data: of({ errorRequest: new ErrorRequest('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [ErrorRequestDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ErrorRequestDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ErrorRequestDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.errorRequest).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
