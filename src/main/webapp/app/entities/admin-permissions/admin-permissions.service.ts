import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminPermissions } from 'app/shared/model/admin-permissions.model';

type EntityResponseType = HttpResponse<IAdminPermissions>;
type EntityArrayResponseType = HttpResponse<IAdminPermissions[]>;

@Injectable({ providedIn: 'root' })
export class AdminPermissionsService {
  public resourceUrl = SERVER_API_URL + 'api/admin-permissions';

  constructor(protected http: HttpClient) {}

  create(adminPermissions: IAdminPermissions): Observable<EntityResponseType> {
    return this.http.post<IAdminPermissions>(this.resourceUrl, adminPermissions, { observe: 'response' });
  }

  update(adminPermissions: IAdminPermissions): Observable<EntityResponseType> {
    return this.http.put<IAdminPermissions>(this.resourceUrl, adminPermissions, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAdminPermissions>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAdminPermissions[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
