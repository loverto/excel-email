import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISheBao } from 'app/shared/model/she-bao.model';

type EntityResponseType = HttpResponse<ISheBao>;
type EntityArrayResponseType = HttpResponse<ISheBao[]>;

@Injectable({ providedIn: 'root' })
export class SheBaoService {
  public resourceUrl = SERVER_API_URL + 'api/she-baos';

  constructor(protected http: HttpClient) {}

  create(sheBao: ISheBao): Observable<EntityResponseType> {
    return this.http.post<ISheBao>(this.resourceUrl, sheBao, { observe: 'response' });
  }

  update(sheBao: ISheBao): Observable<EntityResponseType> {
    return this.http.put<ISheBao>(this.resourceUrl, sheBao, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISheBao>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISheBao[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
