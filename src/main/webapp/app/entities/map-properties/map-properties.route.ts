import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MapProperties } from 'app/shared/model/map-properties.model';
import { MapPropertiesService } from './map-properties.service';
import { MapPropertiesComponent } from './map-properties.component';
import { MapPropertiesDetailComponent } from './map-properties-detail.component';
import { MapPropertiesUpdateComponent } from './map-properties-update.component';
import { MapPropertiesDeletePopupComponent } from './map-properties-delete-dialog.component';
import { IMapProperties } from 'app/shared/model/map-properties.model';

@Injectable({ providedIn: 'root' })
export class MapPropertiesResolve implements Resolve<IMapProperties> {
  constructor(private service: MapPropertiesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMapProperties> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MapProperties>) => response.ok),
        map((mapProperties: HttpResponse<MapProperties>) => mapProperties.body)
      );
    }
    return of(new MapProperties());
  }
}

export const mapPropertiesRoute: Routes = [
  {
    path: '',
    component: MapPropertiesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.mapProperties.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MapPropertiesDetailComponent,
    resolve: {
      mapProperties: MapPropertiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.mapProperties.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MapPropertiesUpdateComponent,
    resolve: {
      mapProperties: MapPropertiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.mapProperties.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MapPropertiesUpdateComponent,
    resolve: {
      mapProperties: MapPropertiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.mapProperties.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mapPropertiesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MapPropertiesDeletePopupComponent,
    resolve: {
      mapProperties: MapPropertiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.mapProperties.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
