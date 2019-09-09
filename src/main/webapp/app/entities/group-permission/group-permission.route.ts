import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from './group-permission.service';
import { GroupPermissionComponent } from './group-permission.component';
import { GroupPermissionDetailComponent } from './group-permission-detail.component';
import { GroupPermissionUpdateComponent } from './group-permission-update.component';
import { GroupPermissionDeletePopupComponent } from './group-permission-delete-dialog.component';
import { IGroupPermission } from 'app/shared/model/group-permission.model';

@Injectable({ providedIn: 'root' })
export class GroupPermissionResolve implements Resolve<IGroupPermission> {
  constructor(private service: GroupPermissionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGroupPermission> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GroupPermission>) => response.ok),
        map((groupPermission: HttpResponse<GroupPermission>) => groupPermission.body)
      );
    }
    return of(new GroupPermission());
  }
}

export const groupPermissionRoute: Routes = [
  {
    path: '',
    component: GroupPermissionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.groupPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupPermissionDetailComponent,
    resolve: {
      groupPermission: GroupPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupPermissionUpdateComponent,
    resolve: {
      groupPermission: GroupPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupPermissionUpdateComponent,
    resolve: {
      groupPermission: GroupPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const groupPermissionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GroupPermissionDeletePopupComponent,
    resolve: {
      groupPermission: GroupPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.groupPermission.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
