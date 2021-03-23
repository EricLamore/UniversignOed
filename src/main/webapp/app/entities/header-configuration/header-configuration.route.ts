import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHeaderConfiguration, HeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { HeaderConfigurationService } from './header-configuration.service';
import { HeaderConfigurationComponent } from './header-configuration.component';
import { HeaderConfigurationDetailComponent } from './header-configuration-detail.component';
import { HeaderConfigurationUpdateComponent } from './header-configuration-update.component';

@Injectable({ providedIn: 'root' })
export class HeaderConfigurationResolve implements Resolve<IHeaderConfiguration> {
  constructor(private service: HeaderConfigurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHeaderConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((headerConfiguration: HttpResponse<HeaderConfiguration>) => {
          if (headerConfiguration.body) {
            return of(headerConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HeaderConfiguration());
  }
}

export const headerConfigurationRoute: Routes = [
  {
    path: '',
    component: HeaderConfigurationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.headerConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HeaderConfigurationDetailComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.headerConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HeaderConfigurationUpdateComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.headerConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HeaderConfigurationUpdateComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.headerConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
