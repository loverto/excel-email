import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGongjijinHistory } from 'app/shared/model/gongjijin-history.model';

type EntityResponseType = HttpResponse<IGongjijinHistory>;
type EntityArrayResponseType = HttpResponse<IGongjijinHistory[]>;

@Injectable({ providedIn: 'root' })
export class GongjijinHistoryService {
  public resourceUrl = SERVER_API_URL + 'api/gongjijin-histories';

  constructor(protected http: HttpClient) {}

  create(gongjijinHistory: IGongjijinHistory): Observable<EntityResponseType> {
    return this.http.post<IGongjijinHistory>(this.resourceUrl, gongjijinHistory, { observe: 'response' });
  }

  update(gongjijinHistory: IGongjijinHistory): Observable<EntityResponseType> {
    return this.http.put<IGongjijinHistory>(this.resourceUrl, gongjijinHistory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGongjijinHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGongjijinHistory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
