import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MailContent } from 'app/shared/model/mail-content.model';
import { MailContentService } from './mail-content.service';
import { MailContentComponent } from './mail-content.component';
import { MailContentDetailComponent } from './mail-content-detail.component';
import { MailContentUpdateComponent } from './mail-content-update.component';
import { MailContentDeletePopupComponent } from './mail-content-delete-dialog.component';
import { IMailContent } from 'app/shared/model/mail-content.model';

@Injectable({ providedIn: 'root' })
export class MailContentResolve implements Resolve<IMailContent> {
  constructor(private service: MailContentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMailContent> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MailContent>) => response.ok),
        map((mailContent: HttpResponse<MailContent>) => mailContent.body)
      );
    }
    return of(new MailContent());
  }
}

export const mailContentRoute: Routes = [
  {
    path: '',
    component: MailContentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.mailContent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MailContentDetailComponent,
    resolve: {
      mailContent: MailContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailContent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MailContentUpdateComponent,
    resolve: {
      mailContent: MailContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailContent.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MailContentUpdateComponent,
    resolve: {
      mailContent: MailContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailContent.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mailContentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MailContentDeletePopupComponent,
    resolve: {
      mailContent: MailContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailContent.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
