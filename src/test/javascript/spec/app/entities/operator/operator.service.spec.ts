import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { OperatorService } from 'app/entities/operator/operator.service';
import { IOperator, Operator } from 'app/shared/model/operator.model';
import { OpearatorStatus } from 'app/shared/model/enumerations/opearator-status.model';
import { SignatureStatus } from 'app/shared/model/enumerations/signature-status.model';
import { UniversignStatus } from 'app/shared/model/enumerations/universign-status.model';

describe('Service Tests', () => {
  describe('Operator Service', () => {
    let injector: TestBed;
    let service: OperatorService;
    let httpMock: HttpTestingController;
    let elemDefault: IOperator;
    let expectedResult: IOperator | IOperator[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        SignatureStatus.READY,
        false,
        'AAAAAAA',
        'AAAAAAA',
        UniversignStatus.STATUS_INACTIVE,
        false,
        false,
        SignatureStatus.READY,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            invitationDate: currentDate.format(DATE_FORMAT),
            accessDate: currentDate.format(DATE_FORMAT),
            trainingDate: currentDate.format(DATE_FORMAT),
            mcqDate: currentDate.format(DATE_FORMAT),
            birthDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find('123').subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Operator', () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            invitationDate: currentDate.format(DATE_FORMAT),
            accessDate: currentDate.format(DATE_FORMAT),
            trainingDate: currentDate.format(DATE_FORMAT),
            mcqDate: currentDate.format(DATE_FORMAT),
            birthDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invitationDate: currentDate,
            accessDate: currentDate,
            trainingDate: currentDate,
            mcqDate: currentDate,
            birthDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Operator()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Operator', () => {
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
            language: 'BBBBBB',
            groupId: 'BBBBBB',
            groupName: 'BBBBBB',
            signatureStatus: 'BBBBBB',
            isOED: true,
            signatureSessionId: 'BBBBBB',
            signatureSessionURL: 'BBBBBB',
            universignStatus: 'BBBBBB',
            certified: true,
            qualified: true,
            previousFinalUniversignStatus: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            emailAddress: 'BBBBBB',
            birthDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invitationDate: currentDate,
            accessDate: currentDate,
            trainingDate: currentDate,
            mcqDate: currentDate,
            birthDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Operator', () => {
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
            language: 'BBBBBB',
            groupId: 'BBBBBB',
            groupName: 'BBBBBB',
            signatureStatus: 'BBBBBB',
            isOED: true,
            signatureSessionId: 'BBBBBB',
            signatureSessionURL: 'BBBBBB',
            universignStatus: 'BBBBBB',
            certified: true,
            qualified: true,
            previousFinalUniversignStatus: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            emailAddress: 'BBBBBB',
            birthDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            invitationDate: currentDate,
            accessDate: currentDate,
            trainingDate: currentDate,
            mcqDate: currentDate,
            birthDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Operator', () => {
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
