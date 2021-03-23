import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdministrator } from 'app/shared/model/administrator.model';

type EntityResponseType = HttpResponse<IAdministrator>;
type EntityArrayResponseType = HttpResponse<IAdministrator[]>;

@Injectable({ providedIn: 'root' })
export class AdministratorService {
  public resourceUrl = SERVER_API_URL + 'api/administrators';

  constructor(protected http: HttpClient) {}

  create(administrator: IAdministrator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(administrator);
    return this.http
      .post<IAdministrator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(administrator: IAdministrator): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(administrator);
    return this.http
      .put<IAdministrator>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IAdministrator>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAdministrator[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(administrator: IAdministrator): IAdministrator {
    const copy: IAdministrator = Object.assign({}, administrator, {
      birthDate: administrator.birthDate && administrator.birthDate.isValid() ? administrator.birthDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthDate = res.body.birthDate ? moment(res.body.birthDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((administrator: IAdministrator) => {
        administrator.birthDate = administrator.birthDate ? moment(administrator.birthDate) : undefined;
      });
    }
    return res;
  }
}