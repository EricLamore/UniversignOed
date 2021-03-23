import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGroupPermission, GroupPermission } from 'app/shared/model/group-permission.model';
import { GroupPermissionService } from './group-permission.service';
import { GroupPermissionComponent } from './group-permission.component';
import { GroupPermissionDetailComponent } from './group-permission-detail.component';
import { GroupPermissionUpdateComponent } from './group-permission-update.component';

@Injectable({ providedIn: 'root' })
export class GroupPermissionResolve implements Resolve<IGroupPermission> {
  constructor(private service: GroupPermissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupPermission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((groupPermission: HttpResponse<GroupPermission>) => {
          if (groupPermission.body) {
            return of(groupPermission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GroupPermission());
  }
}

export const groupPermissionRoute: Routes = [
  {
    path: '',
    component: GroupPermissionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.groupPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GroupPermissionDetailComponent,
    resolve: {
      groupPermission: GroupPermissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.groupPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GroupPermissionUpdateComponent,
    resolve: {
      groupPermission: GroupPermissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.groupPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GroupPermissionUpdateComponent,
    resolve: {
      groupPermission: GroupPermissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.groupPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
