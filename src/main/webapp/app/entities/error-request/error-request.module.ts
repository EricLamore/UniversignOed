import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  ErrorRequestComponent,
  ErrorRequestDetailComponent,
  ErrorRequestUpdateComponent,
  ErrorRequestDeletePopupComponent,
  ErrorRequestDeleteDialogComponent,
  errorRequestRoute,
  errorRequestPopupRoute
} from './';

const ENTITY_STATES = [...errorRequestRoute, ...errorRequestPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ErrorRequestComponent,
    ErrorRequestDetailComponent,
    ErrorRequestUpdateComponent,
    ErrorRequestDeleteDialogComponent,
    ErrorRequestDeletePopupComponent
  ],
  entryComponents: [
    ErrorRequestComponent,
    ErrorRequestUpdateComponent,
    ErrorRequestDeleteDialogComponent,
    ErrorRequestDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedErrorRequestModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
