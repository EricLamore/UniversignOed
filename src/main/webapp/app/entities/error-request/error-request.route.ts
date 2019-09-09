import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ErrorRequest } from 'app/shared/model/error-request.model';
import { ErrorRequestService } from './error-request.service';
import { ErrorRequestComponent } from './error-request.component';
import { ErrorRequestDetailComponent } from './error-request-detail.component';
import { ErrorRequestUpdateComponent } from './error-request-update.component';
import { ErrorRequestDeletePopupComponent } from './error-request-delete-dialog.component';
import { IErrorRequest } from 'app/shared/model/error-request.model';

@Injectable({ providedIn: 'root' })
export class ErrorRequestResolve implements Resolve<IErrorRequest> {
  constructor(private service: ErrorRequestService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IErrorRequest> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ErrorRequest>) => response.ok),
        map((errorRequest: HttpResponse<ErrorRequest>) => errorRequest.body)
      );
    }
    return of(new ErrorRequest());
  }
}

export const errorRequestRoute: Routes = [
  {
    path: '',
    component: ErrorRequestComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.errorRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ErrorRequestDetailComponent,
    resolve: {
      errorRequest: ErrorRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.errorRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ErrorRequestUpdateComponent,
    resolve: {
      errorRequest: ErrorRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.errorRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ErrorRequestUpdateComponent,
    resolve: {
      errorRequest: ErrorRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.errorRequest.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const errorRequestPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ErrorRequestDeletePopupComponent,
    resolve: {
      errorRequest: ErrorRequestResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.errorRequest.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
