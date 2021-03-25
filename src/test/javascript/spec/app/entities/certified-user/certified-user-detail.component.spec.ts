import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { CertifiedUserDetailComponent } from 'app/entities/certified-user/certified-user-detail.component';
import { CertifiedUser } from 'app/shared/model/certified-user.model';

describe('Component Tests', () => {
  describe('CertifiedUser Management Detail Component', () => {
    let comp: CertifiedUserDetailComponent;
    let fixture: ComponentFixture<CertifiedUserDetailComponent>;
    const route = ({ data: of({ certifiedUser: new CertifiedUser('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [CertifiedUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CertifiedUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CertifiedUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load certifiedUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.certifiedUser).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
