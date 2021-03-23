import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICertifiedUser, CertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from './certified-user.service';
import { CertifiedUserComponent } from './certified-user.component';
import { CertifiedUserDetailComponent } from './certified-user-detail.component';
import { CertifiedUserUpdateComponent } from './certified-user-update.component';

@Injectable({ providedIn: 'root' })
export class CertifiedUserResolve implements Resolve<ICertifiedUser> {
  constructor(private service: CertifiedUserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICertifiedUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((certifiedUser: HttpResponse<CertifiedUser>) => {
          if (certifiedUser.body) {
            return of(certifiedUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CertifiedUser());
  }
}

export const certifiedUserRoute: Routes = [
  {
    path: '',
    component: CertifiedUserComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.certifiedUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CertifiedUserDetailComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.certifiedUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CertifiedUserUpdateComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.certifiedUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CertifiedUserUpdateComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.certifiedUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
