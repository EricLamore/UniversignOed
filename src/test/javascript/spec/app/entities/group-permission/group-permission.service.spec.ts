import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GroupPermissionService } from 'app/entities/group-permission/group-permission.service';
import { IGroupPermission, GroupPermission } from 'app/shared/model/group-permission.model';

describe('Service Tests', () => {
  describe('GroupPermission Service', () => {
    let injector: TestBed;
    let service: GroupPermissionService;
    let httpMock: HttpTestingController;
    let elemDefault: IGroupPermission;
    let expectedResult: IGroupPermission | IGroupPermission[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GroupPermissionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new GroupPermission('ID', false, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GroupPermission', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new GroupPermission()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GroupPermission', () => {
        const returnedFromService = Object.assign(
          {
            moveOperatorsToUniversignOrganization: true,
            allowCustomization: true,
            allowAffiliatedGroup: true,
            allowAffiliatedCustomization: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GroupPermission', () => {
        const returnedFromService = Object.assign(
          {
            moveOperatorsToUniversignOrganization: true,
            allowCustomization: true,
            allowAffiliatedGroup: true,
            allowAffiliatedCustomization: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GroupPermission', () => {
        service.delete('123').subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
