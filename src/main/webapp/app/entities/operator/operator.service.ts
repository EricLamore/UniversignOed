import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperator } from 'app/shared/model/operator.model';

type EntityResponseType = HttpResponse<IOperator>;
type EntityArrayResponseType = HttpResponse<IOperator[]>;

@Injectable({ providedIn: 'root' })
export class OperatorService {
  public resourceUrl = SERVER_API_URL + 'api/operators';

  constructor(protected http: HttpClient) {}

  create(operator: IOperator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operator);
    return this.http
      .post<IOperator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(operator: IOperator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operator);
    return this.http
      .put<IOperator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IOperator>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOperator[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(operator: IOperator): IOperator {
    const copy: IOperator = Object.assign({}, operator, {
      invitationDate:
        operator.invitationDate && operator.invitationDate.isValid() ? operator.invitationDate.format(DATE_FORMAT) : undefined,
      accessDate: operator.accessDate && operator.accessDate.isValid() ? operator.accessDate.format(DATE_FORMAT) : undefined,
      trainingDate: operator.trainingDate && operator.trainingDate.isValid() ? operator.trainingDate.format(DATE_FORMAT) : undefined,
      mcqDate: operator.mcqDate && operator.mcqDate.isValid() ? operator.mcqDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.invitationDate = res.body.invitationDate ? moment(res.body.invitationDate) : undefined;
      res.body.accessDate = res.body.accessDate ? moment(res.body.accessDate) : undefined;
      res.body.trainingDate = res.body.trainingDate ? moment(res.body.trainingDate) : undefined;
      res.body.mcqDate = res.body.mcqDate ? moment(res.body.mcqDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((operator: IOperator) => {
        operator.invitationDate = operator.invitationDate ? moment(operator.invitationDate) : undefined;
        operator.accessDate = operator.accessDate ? moment(operator.accessDate) : undefined;
        operator.trainingDate = operator.trainingDate ? moment(operator.trainingDate) : undefined;
        operator.mcqDate = operator.mcqDate ? moment(operator.mcqDate) : undefined;
      });
    }
    return res;
  }
}
