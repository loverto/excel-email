import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMailContent } from 'app/shared/model/mail-content.model';

type EntityResponseType = HttpResponse<IMailContent>;
type EntityArrayResponseType = HttpResponse<IMailContent[]>;

@Injectable({ providedIn: 'root' })
export class MailContentService {
  public resourceUrl = SERVER_API_URL + 'api/mail-contents';

  constructor(protected http: HttpClient) {}

  create(mailContent: IMailContent): Observable<EntityResponseType> {
    return this.http.post<IMailContent>(this.resourceUrl, mailContent, { observe: 'response' });
  }

  update(mailContent: IMailContent): Observable<EntityResponseType> {
    return this.http.put<IMailContent>(this.resourceUrl, mailContent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMailContent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMailContent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
