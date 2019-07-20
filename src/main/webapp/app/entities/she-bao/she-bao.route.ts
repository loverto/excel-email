import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SheBao } from 'app/shared/model/she-bao.model';
import { SheBaoService } from './she-bao.service';
import { SheBaoComponent } from './she-bao.component';
import { SheBaoDetailComponent } from './she-bao-detail.component';
import { SheBaoUpdateComponent } from './she-bao-update.component';
import { SheBaoDeletePopupComponent } from './she-bao-delete-dialog.component';
import { ISheBao } from 'app/shared/model/she-bao.model';

@Injectable({ providedIn: 'root' })
export class SheBaoResolve implements Resolve<ISheBao> {
  constructor(private service: SheBaoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISheBao> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<SheBao>) => response.ok),
        map((sheBao: HttpResponse<SheBao>) => sheBao.body)
      );
    }
    return of(new SheBao());
  }
}

export const sheBaoRoute: Routes = [
  {
    path: '',
    component: SheBaoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'excelEmailApp.sheBao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SheBaoDetailComponent,
    resolve: {
      sheBao: SheBaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SheBaoUpdateComponent,
    resolve: {
      sheBao: SheBaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBao.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SheBaoUpdateComponent,
    resolve: {
      sheBao: SheBaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBao.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sheBaoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SheBaoDeletePopupComponent,
    resolve: {
      sheBao: SheBaoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'excelEmailApp.sheBao.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
