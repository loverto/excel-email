import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMailConfig } from 'app/shared/model/mail-config.model';

type EntityResponseType = HttpResponse<IMailConfig>;
type EntityArrayResponseType = HttpResponse<IMailConfig[]>;

@Injectable({ providedIn: 'root' })
export class MailConfigService {
  public resourceUrl = SERVER_API_URL + 'api/mail-configs';

  constructor(protected http: HttpClient) {}

  create(mailConfig: IMailConfig): Observable<EntityResponseType> {
    return this.http.post<IMailConfig>(this.resourceUrl, mailConfig, { observe: 'response' });
  }

  update(mailConfig: IMailConfig): Observable<EntityResponseType> {
    return this.http.put<IMailConfig>(this.resourceUrl, mailConfig, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMailConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMailConfig[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
