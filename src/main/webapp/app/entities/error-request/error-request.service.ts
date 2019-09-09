import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IErrorRequest } from 'app/shared/model/error-request.model';

type EntityResponseType = HttpResponse<IErrorRequest>;
type EntityArrayResponseType = HttpResponse<IErrorRequest[]>;

@Injectable({ providedIn: 'root' })
export class ErrorRequestService {
  public resourceUrl = SERVER_API_URL + 'api/error-requests';

  constructor(protected http: HttpClient) {}

  create(errorRequest: IErrorRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(errorRequest);
    return this.http
      .post<IErrorRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(errorRequest: IErrorRequest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(errorRequest);
    return this.http
      .put<IErrorRequest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IErrorRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IErrorRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(errorRequest: IErrorRequest): IErrorRequest {
    const copy: IErrorRequest = Object.assign({}, errorRequest, {
      birthDate: errorRequest.birthDate != null && errorRequest.birthDate.isValid() ? errorRequest.birthDate.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthDate = res.body.birthDate != null ? moment(res.body.birthDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((errorRequest: IErrorRequest) => {
        errorRequest.birthDate = errorRequest.birthDate != null ? moment(errorRequest.birthDate) : null;
      });
    }
    return res;
  }
}
