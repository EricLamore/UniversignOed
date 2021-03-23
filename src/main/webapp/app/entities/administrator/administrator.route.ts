import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdministrator, Administrator } from 'app/shared/model/administrator.model';
import { AdministratorService } from './administrator.service';
import { AdministratorComponent } from './administrator.component';
import { AdministratorDetailComponent } from './administrator-detail.component';
import { AdministratorUpdateComponent } from './administrator-update.component';

@Injectable({ providedIn: 'root' })
export class AdministratorResolve implements Resolve<IAdministrator> {
  constructor(private service: AdministratorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdministrator> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((administrator: HttpResponse<Administrator>) => {
          if (administrator.body) {
            return of(administrator.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Administrator());
  }
}

export const administratorRoute: Routes = [
  {
    path: '',
    component: AdministratorComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.administrator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdministratorDetailComponent,
    resolve: {
      administrator: AdministratorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.administrator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdministratorUpdateComponent,
    resolve: {
      administrator: AdministratorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.administrator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdministratorUpdateComponent,
    resolve: {
      administrator: AdministratorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.administrator.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
