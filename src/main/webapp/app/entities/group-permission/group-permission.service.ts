import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroupPermission } from 'app/shared/model/group-permission.model';

type EntityResponseType = HttpResponse<IGroupPermission>;
type EntityArrayResponseType = HttpResponse<IGroupPermission[]>;

@Injectable({ providedIn: 'root' })
export class GroupPermissionService {
  public resourceUrl = SERVER_API_URL + 'api/group-permissions';

  constructor(protected http: HttpClient) {}

  create(groupPermission: IGroupPermission): Observable<EntityResponseType> {
    return this.http.post<IGroupPermission>(this.resourceUrl, groupPermission, { observe: 'response' });
  }

  update(groupPermission: IGroupPermission): Observable<EntityResponseType> {
    return this.http.put<IGroupPermission>(this.resourceUrl, groupPermission, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IGroupPermission>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGroupPermission[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
