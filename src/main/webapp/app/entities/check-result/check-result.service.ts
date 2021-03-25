import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICheckResult } from 'app/shared/model/check-result.model';

type EntityResponseType = HttpResponse<ICheckResult>;
type EntityArrayResponseType = HttpResponse<ICheckResult[]>;

@Injectable({ providedIn: 'root' })
export class CheckResultService {
  public resourceUrl = SERVER_API_URL + 'api/check-results';

  constructor(protected http: HttpClient) {}

  create(checkResult: ICheckResult): Observable<EntityResponseType> {
    return this.http.post<ICheckResult>(this.resourceUrl, checkResult, { observe: 'response' });
  }

  update(checkResult: ICheckResult): Observable<EntityResponseType> {
    return this.http.put<ICheckResult>(this.resourceUrl, checkResult, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ICheckResult>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICheckResult[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
