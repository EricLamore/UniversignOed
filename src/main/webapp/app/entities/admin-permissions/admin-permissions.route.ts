import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdminPermissions, AdminPermissions } from 'app/shared/model/admin-permissions.model';
import { AdminPermissionsService } from './admin-permissions.service';
import { AdminPermissionsComponent } from './admin-permissions.component';
import { AdminPermissionsDetailComponent } from './admin-permissions-detail.component';
import { AdminPermissionsUpdateComponent } from './admin-permissions-update.component';

@Injectable({ providedIn: 'root' })
export class AdminPermissionsResolve implements Resolve<IAdminPermissions> {
  constructor(private service: AdminPermissionsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminPermissions> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adminPermissions: HttpResponse<AdminPermissions>) => {
          if (adminPermissions.body) {
            return of(adminPermissions.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AdminPermissions());
  }
}

export const adminPermissionsRoute: Routes = [
  {
    path: '',
    component: AdminPermissionsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.adminPermissions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdminPermissionsDetailComponent,
    resolve: {
      adminPermissions: AdminPermissionsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.adminPermissions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdminPermissionsUpdateComponent,
    resolve: {
      adminPermissions: AdminPermissionsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.adminPermissions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdminPermissionsUpdateComponent,
    resolve: {
      adminPermissions: AdminPermissionsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.adminPermissions.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
