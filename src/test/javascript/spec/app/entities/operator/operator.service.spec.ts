/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OperatorService } from 'app/entities/operator/operator.service';
import { IOperator, Operator, OpearatorStatus } from 'app/shared/model/operator.model';

describe('Service Tests', () => {
  describe('Operator Service', () => {
    let injector: TestBed;
    let service: OperatorService;
    let httpMock: HttpTestingController;
    let elemDefault: IOperator;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(OperatorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Operator(
        'ID',
        OpearatorStatus.CREATED,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        false,
        currentDate,
        false,
        currentDate,
        false,
        false,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            invitationDate: currentDate.format(DATE_FORMAT),
            accessDate: currentDate.format(DATE_FORMAT),
            trainingDate: currentDate.format(DATE_FORMAT),
            mcqDate: currentDate.format(DATE_FORMAT)
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

      it('should create a Operator', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            invitationDate: currentDate.format(DATE_FORMAT),
            accessDate: currentDate.format(DATE_FORMAT),
            trainingDate: currentDate.format(DATE_FORMAT),
            mcqDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            invitationDate: currentDate,
            accessDate: currentDate,
            trainingDate: currentDate,
            mcqDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new Operator(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Operator', async () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            invitationDate: currentDate.format(DATE_FORMAT),
            invitationUrl: 'BBBBBB',
            updateAccessDate: true,
            accessDate: currentDate.format(DATE_FORMAT),
            updateTrainingDate: true,
            trainingDate: currentDate.format(DATE_FORMAT),
            hasSucceededMCQ: true,
            updateQCMDate: true,
            mcqDate: currentDate.format(DATE_FORMAT),
            trainingSessionId: 'BBBBBB',
            trainingSessionURL: 'BBBBBB',
            sessionId: 'BBBBBB',
            groupId: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invitationDate: currentDate,
            accessDate: currentDate,
            trainingDate: currentDate,
            mcqDate: currentDate
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

      it('should return a list of Operator', async () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            invitationDate: currentDate.format(DATE_FORMAT),
            invitationUrl: 'BBBBBB',
            updateAccessDate: true,
            accessDate: currentDate.format(DATE_FORMAT),
            updateTrainingDate: true,
            trainingDate: currentDate.format(DATE_FORMAT),
            hasSucceededMCQ: true,
            updateQCMDate: true,
            mcqDate: currentDate.format(DATE_FORMAT),
            trainingSessionId: 'BBBBBB',
            trainingSessionURL: 'BBBBBB',
            sessionId: 'BBBBBB',
            groupId: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            invitationDate: currentDate,
            accessDate: currentDate,
            trainingDate: currentDate,
            mcqDate: currentDate
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

      it('should delete a Operator', async () => {
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
