import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroup } from 'app/shared/model/group.model';

type EntityResponseType = HttpResponse<IGroup>;
type EntityArrayResponseType = HttpResponse<IGroup[]>;

@Injectable({ providedIn: 'root' })
export class GroupService {
  public resourceUrl = SERVER_API_URL + 'api/groups';

  constructor(protected http: HttpClient) {}

  create(group: IGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(group);
    return this.http
      .post<IGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(group: IGroup): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(group);
    return this.http
      .put<IGroup>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGroup[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(group: IGroup): IGroup {
    const copy: IGroup = Object.assign({}, group, {
      createdAt: group.createdAt && group.createdAt.isValid() ? group.createdAt.toJSON() : undefined,
      lastUpdatedAt: group.lastUpdatedAt && group.lastUpdatedAt.isValid() ? group.lastUpdatedAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
      res.body.lastUpdatedAt = res.body.lastUpdatedAt ? moment(res.body.lastUpdatedAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((group: IGroup) => {
        group.createdAt = group.createdAt ? moment(group.createdAt) : undefined;
        group.lastUpdatedAt = group.lastUpdatedAt ? moment(group.lastUpdatedAt) : undefined;
      });
    }
    return res;
  }
}
