import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProperties, Properties } from 'app/shared/model/properties.model';
import { PropertiesService } from './properties.service';
import { PropertiesComponent } from './properties.component';
import { PropertiesDetailComponent } from './properties-detail.component';
import { PropertiesUpdateComponent } from './properties-update.component';

@Injectable({ providedIn: 'root' })
export class PropertiesResolve implements Resolve<IProperties> {
  constructor(private service: PropertiesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProperties> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((properties: HttpResponse<Properties>) => {
          if (properties.body) {
            return of(properties.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Properties());
  }
}

export const propertiesRoute: Routes = [
  {
    path: '',
    component: PropertiesComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.properties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PropertiesDetailComponent,
    resolve: {
      properties: PropertiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.properties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PropertiesUpdateComponent,
    resolve: {
      properties: PropertiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.properties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PropertiesUpdateComponent,
    resolve: {
      properties: PropertiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.properties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
