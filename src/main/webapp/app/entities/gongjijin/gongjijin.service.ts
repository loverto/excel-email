import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGongjijin } from 'app/shared/model/gongjijin.model';

type EntityResponseType = HttpResponse<IGongjijin>;
type EntityArrayResponseType = HttpResponse<IGongjijin[]>;

@Injectable({ providedIn: 'root' })
export class GongjijinService {
  public resourceUrl = SERVER_API_URL + 'api/gongjijins';

  constructor(protected http: HttpClient) {}

  create(gongjijin: IGongjijin): Observable<EntityResponseType> {
    return this.http.post<IGongjijin>(this.resourceUrl, gongjijin, { observe: 'response' });
  }

  update(gongjijin: IGongjijin): Observable<EntityResponseType> {
    return this.http.put<IGongjijin>(this.resourceUrl, gongjijin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGongjijin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGongjijin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
