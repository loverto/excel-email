import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SheBaoHistory } from 'app/shared/model/she-bao-history.model';
import { SheBaoHistoryService } from './she-bao-history.service';
import { SheBaoHistoryComponent } from './she-bao-history.component';
import { SheBaoHistoryDetailComponent } from './she-bao-history-detail.component';
import { SheBaoHistoryUpdateComponent } from './she-bao-history-update.component';
import { SheBaoHistoryDeletePopupComponent } from './she-bao-history-delete-dialog.component';
import { ISheBaoHistory } from 'app/shared/model/she-bao-history.model';

@Injectable({ providedIn: 'root' })
export class SheBaoHistoryResolve implements Resolve<ISheBaoHistory> {
  constructor(private service: SheBaoHistoryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISheBaoHistory> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SheBaoHistory>) => response.ok),
        map((sheBaoHistory: HttpResponse<SheBaoHistory>) => sheBaoHistory.body)
      );
    }
    return of(new SheBaoHistory());
  }
}

export const sheBaoHistoryRoute: Routes = [
  {
    path: '',
    component: SheBaoHistoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.sheBaoHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SheBaoHistoryDetailComponent,
    resolve: {
      sheBaoHistory: SheBaoHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBaoHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SheBaoHistoryUpdateComponent,
    resolve: {
      sheBaoHistory: SheBaoHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBaoHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SheBaoHistoryUpdateComponent,
    resolve: {
      sheBaoHistory: SheBaoHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBaoHistory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sheBaoHistoryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SheBaoHistoryDeletePopupComponent,
    resolve: {
      sheBaoHistory: SheBaoHistoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBaoHistory.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
