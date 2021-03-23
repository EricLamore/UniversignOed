import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { AdminPermissionsDetailComponent } from 'app/entities/admin-permissions/admin-permissions-detail.component';
import { AdminPermissions } from 'app/shared/model/admin-permissions.model';

describe('Component Tests', () => {
  describe('AdminPermissions Management Detail Component', () => {
    let comp: AdminPermissionsDetailComponent;
    let fixture: ComponentFixture<AdminPermissionsDetailComponent>;
    const route = ({ data: of({ adminPermissions: new AdminPermissions('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [AdminPermissionsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdminPermissionsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdminPermissionsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adminPermissions on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adminPermissions).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
