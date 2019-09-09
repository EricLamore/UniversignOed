import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CertifiedUser } from 'app/shared/model/certified-user.model';
import { CertifiedUserService } from './certified-user.service';
import { CertifiedUserComponent } from './certified-user.component';
import { CertifiedUserDetailComponent } from './certified-user-detail.component';
import { CertifiedUserUpdateComponent } from './certified-user-update.component';
import { CertifiedUserDeletePopupComponent } from './certified-user-delete-dialog.component';
import { ICertifiedUser } from 'app/shared/model/certified-user.model';

@Injectable({ providedIn: 'root' })
export class CertifiedUserResolve implements Resolve<ICertifiedUser> {
  constructor(private service: CertifiedUserService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICertifiedUser> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CertifiedUser>) => response.ok),
        map((certifiedUser: HttpResponse<CertifiedUser>) => certifiedUser.body)
      );
    }
    return of(new CertifiedUser());
  }
}

export const certifiedUserRoute: Routes = [
  {
    path: '',
    component: CertifiedUserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.certifiedUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CertifiedUserDetailComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.certifiedUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CertifiedUserUpdateComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.certifiedUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CertifiedUserUpdateComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.certifiedUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const certifiedUserPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CertifiedUserDeletePopupComponent,
    resolve: {
      certifiedUser: CertifiedUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.certifiedUser.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
