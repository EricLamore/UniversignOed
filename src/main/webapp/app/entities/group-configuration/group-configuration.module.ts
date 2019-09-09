import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  GroupConfigurationComponent,
  GroupConfigurationDetailComponent,
  GroupConfigurationUpdateComponent,
  GroupConfigurationDeletePopupComponent,
  GroupConfigurationDeleteDialogComponent,
  groupConfigurationRoute,
  groupConfigurationPopupRoute
} from './';

const ENTITY_STATES = [...groupConfigurationRoute, ...groupConfigurationPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GroupConfigurationComponent,
    GroupConfigurationDetailComponent,
    GroupConfigurationUpdateComponent,
    GroupConfigurationDeleteDialogComponent,
    GroupConfigurationDeletePopupComponent
  ],
  entryComponents: [
    GroupConfigurationComponent,
    GroupConfigurationUpdateComponent,
    GroupConfigurationDeleteDialogComponent,
    GroupConfigurationDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedGroupConfigurationModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
