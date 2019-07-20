import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Gongjijin } from 'app/shared/model/gongjijin.model';
import { GongjijinService } from './gongjijin.service';
import { GongjijinComponent } from './gongjijin.component';
import { GongjijinDetailComponent } from './gongjijin-detail.component';
import { GongjijinUpdateComponent } from './gongjijin-update.component';
import { GongjijinDeletePopupComponent } from './gongjijin-delete-dialog.component';
import { IGongjijin } from 'app/shared/model/gongjijin.model';

@Injectable({ providedIn: 'root' })
export class GongjijinResolve implements Resolve<IGongjijin> {
  constructor(private service: GongjijinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGongjijin> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Gongjijin>) => response.ok),
        map((gongjijin: HttpResponse<Gongjijin>) => gongjijin.body)
      );
    }
    return of(new Gongjijin());
  }
}

export const gongjijinRoute: Routes = [
  {
    path: '',
    component: GongjijinComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.gongjijin.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GongjijinDetailComponent,
    resolve: {
      gongjijin: GongjijinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijin.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GongjijinUpdateComponent,
    resolve: {
      gongjijin: GongjijinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijin.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GongjijinUpdateComponent,
    resolve: {
      gongjijin: GongjijinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijin.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gongjijinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GongjijinDeletePopupComponent,
    resolve: {
      gongjijin: GongjijinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.gongjijin.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
