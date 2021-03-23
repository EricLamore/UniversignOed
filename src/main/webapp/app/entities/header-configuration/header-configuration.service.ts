import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHeaderConfiguration } from 'app/shared/model/header-configuration.model';

type EntityResponseType = HttpResponse<IHeaderConfiguration>;
type EntityArrayResponseType = HttpResponse<IHeaderConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class HeaderConfigurationService {
  public resourceUrl = SERVER_API_URL + 'api/header-configurations';

  constructor(protected http: HttpClient) {}

  create(headerConfiguration: IHeaderConfiguration): Observable<EntityResponseType> {
    return this.http.post<IHeaderConfiguration>(this.resourceUrl, headerConfiguration, { observe: 'response' });
  }

  update(headerConfiguration: IHeaderConfiguration): Observable<EntityResponseType> {
    return this.http.put<IHeaderConfiguration>(this.resourceUrl, headerConfiguration, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IHeaderConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHeaderConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
