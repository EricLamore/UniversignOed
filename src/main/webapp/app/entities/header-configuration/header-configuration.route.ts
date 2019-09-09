import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { HeaderConfigurationService } from './header-configuration.service';
import { HeaderConfigurationComponent } from './header-configuration.component';
import { HeaderConfigurationDetailComponent } from './header-configuration-detail.component';
import { HeaderConfigurationUpdateComponent } from './header-configuration-update.component';
import { HeaderConfigurationDeletePopupComponent } from './header-configuration-delete-dialog.component';
import { IHeaderConfiguration } from 'app/shared/model/header-configuration.model';

@Injectable({ providedIn: 'root' })
export class HeaderConfigurationResolve implements Resolve<IHeaderConfiguration> {
  constructor(private service: HeaderConfigurationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHeaderConfiguration> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<HeaderConfiguration>) => response.ok),
        map((headerConfiguration: HttpResponse<HeaderConfiguration>) => headerConfiguration.body)
      );
    }
    return of(new HeaderConfiguration());
  }
}

export const headerConfigurationRoute: Routes = [
  {
    path: '',
    component: HeaderConfigurationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.headerConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HeaderConfigurationDetailComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.headerConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HeaderConfigurationUpdateComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.headerConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HeaderConfigurationUpdateComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.headerConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const headerConfigurationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: HeaderConfigurationDeletePopupComponent,
    resolve: {
      headerConfiguration: HeaderConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.headerConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
