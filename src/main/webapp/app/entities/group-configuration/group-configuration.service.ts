import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';

type EntityResponseType = HttpResponse<IGroupConfiguration>;
type EntityArrayResponseType = HttpResponse<IGroupConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class GroupConfigurationService {
  public resourceUrl = SERVER_API_URL + 'api/group-configurations';

  constructor(protected http: HttpClient) {}

  create(groupConfiguration: IGroupConfiguration): Observable<EntityResponseType> {
    return this.http.post<IGroupConfiguration>(this.resourceUrl, groupConfiguration, { observe: 'response' });
  }

  update(groupConfiguration: IGroupConfiguration): Observable<EntityResponseType> {
    return this.http.put<IGroupConfiguration>(this.resourceUrl, groupConfiguration, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IGroupConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGroupConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
