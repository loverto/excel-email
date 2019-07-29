import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISheBaoHistory } from 'app/shared/model/she-bao-history.model';

type EntityResponseType = HttpResponse<ISheBaoHistory>;
type EntityArrayResponseType = HttpResponse<ISheBaoHistory[]>;

@Injectable({ providedIn: 'root' })
export class SheBaoHistoryService {
  public resourceUrl = SERVER_API_URL + 'api/she-bao-histories';

  constructor(protected http: HttpClient) {}

  create(sheBaoHistory: ISheBaoHistory): Observable<EntityResponseType> {
    return this.http.post<ISheBaoHistory>(this.resourceUrl, sheBaoHistory, { observe: 'response' });
  }

  update(sheBaoHistory: ISheBaoHistory): Observable<EntityResponseType> {
    return this.http.put<ISheBaoHistory>(this.resourceUrl, sheBaoHistory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISheBaoHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISheBaoHistory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
