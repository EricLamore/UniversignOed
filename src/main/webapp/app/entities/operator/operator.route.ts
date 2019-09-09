import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Operator } from 'app/shared/model/operator.model';
import { OperatorService } from './operator.service';
import { OperatorComponent } from './operator.component';
import { OperatorDetailComponent } from './operator-detail.component';
import { OperatorUpdateComponent } from './operator-update.component';
import { OperatorDeletePopupComponent } from './operator-delete-dialog.component';
import { IOperator } from 'app/shared/model/operator.model';

@Injectable({ providedIn: 'root' })
export class OperatorResolve implements Resolve<IOperator> {
  constructor(private service: OperatorService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOperator> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Operator>) => response.ok),
        map((operator: HttpResponse<Operator>) => operator.body)
      );
    }
    return of(new Operator());
  }
}

export const operatorRoute: Routes = [
  {
    path: '',
    component: OperatorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.operator.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OperatorDetailComponent,
    resolve: {
      operator: OperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.operator.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OperatorUpdateComponent,
    resolve: {
      operator: OperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.operator.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OperatorUpdateComponent,
    resolve: {
      operator: OperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.operator.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const operatorPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OperatorDeletePopupComponent,
    resolve: {
      operator: OperatorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.operator.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
