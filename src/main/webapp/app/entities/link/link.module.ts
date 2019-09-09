import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  LinkComponent,
  LinkDetailComponent,
  LinkUpdateComponent,
  LinkDeletePopupComponent,
  LinkDeleteDialogComponent,
  linkRoute,
  linkPopupRoute
} from './';

const ENTITY_STATES = [...linkRoute, ...linkPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [LinkComponent, LinkDetailComponent, LinkUpdateComponent, LinkDeleteDialogComponent, LinkDeletePopupComponent],
  entryComponents: [LinkComponent, LinkUpdateComponent, LinkDeleteDialogComponent, LinkDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedLinkModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
