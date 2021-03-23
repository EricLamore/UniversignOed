import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMapProperties, MapProperties } from 'app/shared/model/map-properties.model';
import { MapPropertiesService } from './map-properties.service';
import { MapPropertiesComponent } from './map-properties.component';
import { MapPropertiesDetailComponent } from './map-properties-detail.component';
import { MapPropertiesUpdateComponent } from './map-properties-update.component';

@Injectable({ providedIn: 'root' })
export class MapPropertiesResolve implements Resolve<IMapProperties> {
  constructor(private service: MapPropertiesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMapProperties> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mapProperties: HttpResponse<MapProperties>) => {
          if (mapProperties.body) {
            return of(mapProperties.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MapProperties());
  }
}

export const mapPropertiesRoute: Routes = [
  {
    path: '',
    component: MapPropertiesComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.mapProperties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MapPropertiesDetailComponent,
    resolve: {
      mapProperties: MapPropertiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.mapProperties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MapPropertiesUpdateComponent,
    resolve: {
      mapProperties: MapPropertiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.mapProperties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MapPropertiesUpdateComponent,
    resolve: {
      mapProperties: MapPropertiesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.mapProperties.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
