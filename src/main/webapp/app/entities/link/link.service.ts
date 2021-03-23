import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILink } from 'app/shared/model/link.model';

type EntityResponseType = HttpResponse<ILink>;
type EntityArrayResponseType = HttpResponse<ILink[]>;

@Injectable({ providedIn: 'root' })
export class LinkService {
  public resourceUrl = SERVER_API_URL + 'api/links';

  constructor(protected http: HttpClient) {}

  create(link: ILink): Observable<EntityResponseType> {
    return this.http.post<ILink>(this.resourceUrl, link, { observe: 'response' });
  }

  update(link: ILink): Observable<EntityResponseType> {
    return this.http.put<ILink>(this.resourceUrl, link, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ILink>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILink[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
