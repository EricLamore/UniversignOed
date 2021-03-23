import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { ColumnDetailComponent } from 'app/entities/column/column-detail.component';
import { Column } from 'app/shared/model/column.model';

describe('Component Tests', () => {
  describe('Column Management Detail Component', () => {
    let comp: ColumnDetailComponent;
    let fixture: ComponentFixture<ColumnDetailComponent>;
    const route = ({ data: of({ column: new Column('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [ColumnDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ColumnDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ColumnDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load column on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.column).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
