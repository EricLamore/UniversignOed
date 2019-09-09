import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  HeaderConfigurationComponent,
  HeaderConfigurationDetailComponent,
  HeaderConfigurationUpdateComponent,
  HeaderConfigurationDeletePopupComponent,
  HeaderConfigurationDeleteDialogComponent,
  headerConfigurationRoute,
  headerConfigurationPopupRoute
} from './';

const ENTITY_STATES = [...headerConfigurationRoute, ...headerConfigurationPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    HeaderConfigurationComponent,
    HeaderConfigurationDetailComponent,
    HeaderConfigurationUpdateComponent,
    HeaderConfigurationDeleteDialogComponent,
    HeaderConfigurationDeletePopupComponent
  ],
  entryComponents: [
    HeaderConfigurationComponent,
    HeaderConfigurationUpdateComponent,
    HeaderConfigurationDeleteDialogComponent,
    HeaderConfigurationDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedHeaderConfigurationModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
