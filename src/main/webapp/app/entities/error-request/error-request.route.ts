import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IErrorRequest, ErrorRequest } from 'app/shared/model/error-request.model';
import { ErrorRequestService } from './error-request.service';
import { ErrorRequestComponent } from './error-request.component';
import { ErrorRequestDetailComponent } from './error-request-detail.component';
import { ErrorRequestUpdateComponent } from './error-request-update.component';

@Injectable({ providedIn: 'root' })
export class ErrorRequestResolve implements Resolve<IErrorRequest> {
  constructor(private service: ErrorRequestService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IErrorRequest> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((errorRequest: HttpResponse<ErrorRequest>) => {
          if (errorRequest.body) {
            return of(errorRequest.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ErrorRequest());
  }
}

export const errorRequestRoute: Routes = [
  {
    path: '',
    component: ErrorRequestComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.errorRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ErrorRequestDetailComponent,
    resolve: {
      errorRequest: ErrorRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.errorRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ErrorRequestUpdateComponent,
    resolve: {
      errorRequest: ErrorRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.errorRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ErrorRequestUpdateComponent,
    resolve: {
      errorRequest: ErrorRequestResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.errorRequest.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
