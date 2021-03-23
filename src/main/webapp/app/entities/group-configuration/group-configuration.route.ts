import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGroupConfiguration, GroupConfiguration } from 'app/shared/model/group-configuration.model';
import { GroupConfigurationService } from './group-configuration.service';
import { GroupConfigurationComponent } from './group-configuration.component';
import { GroupConfigurationDetailComponent } from './group-configuration-detail.component';
import { GroupConfigurationUpdateComponent } from './group-configuration-update.component';

@Injectable({ providedIn: 'root' })
export class GroupConfigurationResolve implements Resolve<IGroupConfiguration> {
  constructor(private service: GroupConfigurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((groupConfiguration: HttpResponse<GroupConfiguration>) => {
          if (groupConfiguration.body) {
            return of(groupConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GroupConfiguration());
  }
}

export const groupConfigurationRoute: Routes = [
  {
    path: '',
    component: GroupConfigurationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.groupConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GroupConfigurationDetailComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.groupConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GroupConfigurationUpdateComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.groupConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GroupConfigurationUpdateComponent,
    resolve: {
      groupConfiguration: GroupConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.groupConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
