import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { CheckResultDetailComponent } from 'app/entities/check-result/check-result-detail.component';
import { CheckResult } from 'app/shared/model/check-result.model';

describe('Component Tests', () => {
  describe('CheckResult Management Detail Component', () => {
    let comp: CheckResultDetailComponent;
    let fixture: ComponentFixture<CheckResultDetailComponent>;
    const route = ({ data: of({ checkResult: new CheckResult('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [CheckResultDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CheckResultDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CheckResultDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load checkResult on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.checkResult).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
