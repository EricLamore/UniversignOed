import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMapProperties } from 'app/shared/model/map-properties.model';

type EntityResponseType = HttpResponse<IMapProperties>;
type EntityArrayResponseType = HttpResponse<IMapProperties[]>;

@Injectable({ providedIn: 'root' })
export class MapPropertiesService {
  public resourceUrl = SERVER_API_URL + 'api/map-properties';

  constructor(protected http: HttpClient) {}

  create(mapProperties: IMapProperties): Observable<EntityResponseType> {
    return this.http.post<IMapProperties>(this.resourceUrl, mapProperties, { observe: 'response' });
  }

  update(mapProperties: IMapProperties): Observable<EntityResponseType> {
    return this.http.put<IMapProperties>(this.resourceUrl, mapProperties, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IMapProperties>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMapProperties[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
