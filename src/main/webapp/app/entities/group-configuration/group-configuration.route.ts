import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GroupConfiguration } from 'app/shared/model/group-configuration.model';
import { GroupConfigurationService } from './group-configuration.service';
import { GroupConfigurationComponent } from './group-configuration.component';
import { GroupConfigurationDetailComponent } from './group-configuration-detail.component';
import { GroupConfigurationUpdateComponent } from './group-configuration-update.component';
import { GroupConfigurationDeletePopupComponent } from './group-configuration-delete-dialog.component';
import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';

@Injectable({ providedIn: 'root' })
export class GroupConfigurationResolve implements Resolve<IGroupConfiguration> {
  constructor(private service: GroupConfigurationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGroupConfiguration> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GroupConfiguration>) => response.ok),
        map((groupConfiguration: HttpResponse<GroupConfiguration>) => groupConfiguration.body)
      );
    }
    return of(new GroupConfiguration());
  }
}

export const groupConfigurationRoute: Routes = [
  {
    path: '',
    component: GroupConfigurationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.groupConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupConfigurationDetailComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupConfigurationUpdateComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupConfigurationUpdateComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const groupConfigurationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GroupConfigurationDeletePopupComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupConfiguration.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
