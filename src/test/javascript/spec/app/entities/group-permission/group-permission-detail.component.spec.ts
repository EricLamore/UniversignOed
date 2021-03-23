import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UniversignOedTestModule } from '../../../test.module';
import { GroupPermissionDetailComponent } from 'app/entities/group-permission/group-permission-detail.component';
import { GroupPermission } from 'app/shared/model/group-permission.model';

describe('Component Tests', () => {
  describe('GroupPermission Management Detail Component', () => {
    let comp: GroupPermissionDetailComponent;
    let fixture: ComponentFixture<GroupPermissionDetailComponent>;
    const route = ({ data: of({ groupPermission: new GroupPermission('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UniversignOedTestModule],
        declarations: [GroupPermissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GroupPermissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupPermissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load groupPermission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.groupPermission).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
