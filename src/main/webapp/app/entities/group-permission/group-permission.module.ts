import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  GroupPermissionComponent,
  GroupPermissionDetailComponent,
  GroupPermissionUpdateComponent,
  GroupPermissionDeletePopupComponent,
  GroupPermissionDeleteDialogComponent,
  groupPermissionRoute,
  groupPermissionPopupRoute
} from './';

const ENTITY_STATES = [...groupPermissionRoute, ...groupPermissionPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GroupPermissionComponent,
    GroupPermissionDetailComponent,
    GroupPermissionUpdateComponent,
    GroupPermissionDeleteDialogComponent,
    GroupPermissionDeletePopupComponent
  ],
  entryComponents: [
    GroupPermissionComponent,
    GroupPermissionUpdateComponent,
    GroupPermissionDeleteDialogComponent,
    GroupPermissionDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedGroupPermissionModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
