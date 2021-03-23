import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IColumn, Column } from 'app/shared/model/column.model';
import { ColumnService } from './column.service';
import { ColumnComponent } from './column.component';
import { ColumnDetailComponent } from './column-detail.component';
import { ColumnUpdateComponent } from './column-update.component';

@Injectable({ providedIn: 'root' })
export class ColumnResolve implements Resolve<IColumn> {
  constructor(private service: ColumnService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IColumn> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((column: HttpResponse<Column>) => {
          if (column.body) {
            return of(column.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Column());
  }
}

export const columnRoute: Routes = [
  {
    path: '',
    component: ColumnComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.column.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ColumnDetailComponent,
    resolve: {
      column: ColumnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.column.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ColumnUpdateComponent,
    resolve: {
      column: ColumnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.column.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ColumnUpdateComponent,
    resolve: {
      column: ColumnResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'universignOedApp.column.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
