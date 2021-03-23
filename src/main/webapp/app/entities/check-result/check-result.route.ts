import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICheckResult, CheckResult } from 'app/shared/model/check-result.model';
import { CheckResultService } from './check-result.service';
import { CheckResultComponent } from './check-result.component';
import { CheckResultDetailComponent } from './check-result-detail.component';
import { CheckResultUpdateComponent } from './check-result-update.component';

@Injectable({ providedIn: 'root' })
export class CheckResultResolve implements Resolve<ICheckResult> {
  constructor(private service: CheckResultService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICheckResult> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((checkResult: HttpResponse<CheckResult>) => {
          if (checkResult.body) {
            return of(checkResult.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CheckResult());
  }
}

export const checkResultRoute: Routes = [
  {
    path: '',
    component: CheckResultComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.checkResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CheckResultDetailComponent,
    resolve: {
      checkResult: CheckResultResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.checkResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CheckResultUpdateComponent,
    resolve: {
      checkResult: CheckResultResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.checkResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CheckResultUpdateComponent,
    resolve: {
      checkResult: CheckResultResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.checkResult.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
