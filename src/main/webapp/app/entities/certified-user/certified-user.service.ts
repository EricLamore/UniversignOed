import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICertifiedUser } from 'app/shared/model/certified-user.model';

type EntityResponseType = HttpResponse<ICertifiedUser>;
type EntityArrayResponseType = HttpResponse<ICertifiedUser[]>;

@Injectable({ providedIn: 'root' })
export class CertifiedUserService {
  public resourceUrl = SERVER_API_URL + 'api/certified-users';

  constructor(protected http: HttpClient) {}

  create(certifiedUser: ICertifiedUser): Observable<EntityResponseType> {
    return this.http.post<ICertifiedUser>(this.resourceUrl, certifiedUser, { observe: 'response' });
  }

  update(certifiedUser: ICertifiedUser): Observable<EntityResponseType> {
    return this.http.put<ICertifiedUser>(this.resourceUrl, certifiedUser, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ICertifiedUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICertifiedUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
