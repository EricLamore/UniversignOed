import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
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

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(operator: IOperator): IOperator {
    const copy: IOperator = Object.assign({}, operator, {
      invitationDate:
        operator.invitationDate != null && operator.invitationDate.isValid() ? operator.invitationDate.format(DATE_FORMAT) : null,
      accessDate: operator.accessDate != null && operator.accessDate.isValid() ? operator.accessDate.format(DATE_FORMAT) : null,
      trainingDate: operator.trainingDate != null && operator.trainingDate.isValid() ? operator.trainingDate.format(DATE_FORMAT) : null,
      mcqDate: operator.mcqDate != null && operator.mcqDate.isValid() ? operator.mcqDate.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.invitationDate = res.body.invitationDate != null ? moment(res.body.invitationDate) : null;
      res.body.accessDate = res.body.accessDate != null ? moment(res.body.accessDate) : null;
      res.body.trainingDate = res.body.trainingDate != null ? moment(res.body.trainingDate) : null;
      res.body.mcqDate = res.body.mcqDate != null ? moment(res.body.mcqDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((operator: IOperator) => {
        operator.invitationDate = operator.invitationDate != null ? moment(operator.invitationDate) : null;
        operator.accessDate = operator.accessDate != null ? moment(operator.accessDate) : null;
        operator.trainingDate = operator.trainingDate != null ? moment(operator.trainingDate) : null;
        operator.mcqDate = operator.mcqDate != null ? moment(operator.mcqDate) : null;
      });
    }
    return res;
  }
}
