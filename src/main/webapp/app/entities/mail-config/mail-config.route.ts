import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MailConfig } from 'app/shared/model/mail-config.model';
import { MailConfigService } from './mail-config.service';
import { MailConfigComponent } from './mail-config.component';
import { MailConfigDetailComponent } from './mail-config-detail.component';
import { MailConfigUpdateComponent } from './mail-config-update.component';
import { MailConfigDeletePopupComponent } from './mail-config-delete-dialog.component';
import { IMailConfig } from 'app/shared/model/mail-config.model';

@Injectable({ providedIn: 'root' })
export class MailConfigResolve implements Resolve<IMailConfig> {
  constructor(private service: MailConfigService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMailConfig> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MailConfig>) => response.ok),
        map((mailConfig: HttpResponse<MailConfig>) => mailConfig.body)
      );
    }
    return of(new MailConfig());
  }
}

export const mailConfigRoute: Routes = [
  {
    path: '',
    component: MailConfigComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.mailConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MailConfigDetailComponent,
    resolve: {
      mailConfig: MailConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MailConfigUpdateComponent,
    resolve: {
      mailConfig: MailConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MailConfigUpdateComponent,
    resolve: {
      mailConfig: MailConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailConfig.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mailConfigPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MailConfigDeletePopupComponent,
    resolve: {
      mailConfig: MailConfigResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.mailConfig.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
