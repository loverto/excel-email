import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GongjijinHistory } from 'app/shared/model/gongjijin-history.model';
import { GongjijinHistoryService } from './gongjijin-history.service';
import { GongjijinHistoryComponent } from './gongjijin-history.component';
import { GongjijinHistoryDetailComponent } from './gongjijin-history-detail.component';
import { GongjijinHistoryUpdateComponent } from './gongjijin-history-update.component';
import { GongjijinHistoryDeletePopupComponent } from './gongjijin-history-delete-dialog.component';
import { IGongjijinHistory } from 'app/shared/model/gongjijin-history.model';

@Injectable({ providedIn: 'root' })
export class GongjijinHistoryResolve implements Resolve<IGongjijinHistory> {
  constructor(private service: GongjijinHistoryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGongjijinHistory> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GongjijinHistory>) => response.ok),
        map((gongjijinHistory: HttpResponse<GongjijinHistory>) => gongjijinHistory.body)
      );
    }
    return of(new GongjijinHistory());
  }
}

export const gongjijinHistoryRoute: Routes = [
  {
    path: '',
    component: GongjijinHistoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.gongjijinHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GongjijinHistoryDetailComponent,
    resolve: {
      gongjijinHistory: GongjijinHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijinHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GongjijinHistoryUpdateComponent,
    resolve: {
      gongjijinHistory: GongjijinHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijinHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GongjijinHistoryUpdateComponent,
    resolve: {
      gongjijinHistory: GongjijinHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijinHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gongjijinHistoryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GongjijinHistoryDeletePopupComponent,
    resolve: {
      gongjijinHistory: GongjijinHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijinHistory.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
