/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AdministratorService } from 'app/entities/administrator/administrator.service';
import { IAdministrator, Administrator, Role, AdministratorStatus } from 'app/shared/model/administrator.model';

describe('Service Tests', () => {
  describe('Administrator Service', () => {
    let injector: TestBed;
    let service: AdministratorService;
    let httpMock: HttpTestingController;
    let elemDefault: IAdministrator;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(AdministratorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Administrator(
        'ID',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        Role.SUPER_ADMIN,
        'AAAAAAA',
        AdministratorStatus.REGISTERING,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            birthDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find('123')
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Administrator', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            birthDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            birthDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new Administrator(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Administrator', async () => {
        const returnedFromService = Object.assign(
          {
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            emailAddress: 'BBBBBB',
            birthDate: currentDate.format(DATE_FORMAT),
            role: 'BBBBBB',
            password: 'BBBBBB',
            administratorStatus: 'BBBBBB',
            adminFonction: 'BBBBBB',
            phone: 'BBBBBB',
            mobilePhone: 'BBBBBB',
            description: 'BBBBBB',
            universignAdmin: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Administrator', async () => {
        const returnedFromService = Object.assign(
          {
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            emailAddress: 'BBBBBB',
            birthDate: currentDate.format(DATE_FORMAT),
            role: 'BBBBBB',
            password: 'BBBBBB',
            administratorStatus: 'BBBBBB',
            adminFonction: 'BBBBBB',
            phone: 'BBBBBB',
            mobilePhone: 'BBBBBB',
            description: 'BBBBBB',
            universignAdmin: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            birthDate: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Administrator', async () => {
        const rxPromise = service.delete('123').subscribe(resp => (expectedResult = resp.ok));

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
