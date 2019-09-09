import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Link } from 'app/shared/model/link.model';
import { LinkService } from './link.service';
import { LinkComponent } from './link.component';
import { LinkDetailComponent } from './link-detail.component';
import { LinkUpdateComponent } from './link-update.component';
import { LinkDeletePopupComponent } from './link-delete-dialog.component';
import { ILink } from 'app/shared/model/link.model';

@Injectable({ providedIn: 'root' })
export class LinkResolve implements Resolve<ILink> {
  constructor(private service: LinkService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILink> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Link>) => response.ok),
        map((link: HttpResponse<Link>) => link.body)
      );
    }
    return of(new Link());
  }
}

export const linkRoute: Routes = [
  {
    path: '',
    component: LinkComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'universignOedApp.link.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LinkDetailComponent,
    resolve: {
      link: LinkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.link.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LinkUpdateComponent,
    resolve: {
      link: LinkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.link.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LinkUpdateComponent,
    resolve: {
      link: LinkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.link.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const linkPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LinkDeletePopupComponent,
    resolve: {
      link: LinkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'universignOedApp.link.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
