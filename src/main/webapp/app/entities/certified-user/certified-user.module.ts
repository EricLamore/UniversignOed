import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  CertifiedUserComponent,
  CertifiedUserDetailComponent,
  CertifiedUserUpdateComponent,
  CertifiedUserDeletePopupComponent,
  CertifiedUserDeleteDialogComponent,
  certifiedUserRoute,
  certifiedUserPopupRoute
} from './';

const ENTITY_STATES = [...certifiedUserRoute, ...certifiedUserPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CertifiedUserComponent,
    CertifiedUserDetailComponent,
    CertifiedUserUpdateComponent,
    CertifiedUserDeleteDialogComponent,
    CertifiedUserDeletePopupComponent
  ],
  entryComponents: [
    CertifiedUserComponent,
    CertifiedUserUpdateComponent,
    CertifiedUserDeleteDialogComponent,
    CertifiedUserDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedCertifiedUserModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
