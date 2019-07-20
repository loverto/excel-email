import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from './user-info.service';
import { UserInfoComponent } from './user-info.component';
import { UserInfoDetailComponent } from './user-info-detail.component';
import { UserInfoUpdateComponent } from './user-info-update.component';
import { UserInfoDeletePopupComponent } from './user-info-delete-dialog.component';
import { IUserInfo } from 'app/shared/model/user-info.model';

@Injectable({ providedIn: 'root' })
export class UserInfoResolve implements Resolve<IUserInfo> {
  constructor(private service: UserInfoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUserInfo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<UserInfo>) => response.ok),
        map((userInfo: HttpResponse<UserInfo>) => userInfo.body)
      );
    }
    return of(new UserInfo());
  }
}

export const userInfoRoute: Routes = [
  {
    path: '',
    component: UserInfoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.userInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserInfoDetailComponent,
    resolve: {
      userInfo: UserInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.userInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserInfoUpdateComponent,
    resolve: {
      userInfo: UserInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.userInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserInfoUpdateComponent,
    resolve: {
      userInfo: UserInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.userInfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const userInfoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: UserInfoDeletePopupComponent,
    resolve: {
      userInfo: UserInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.userInfo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
