import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  OperatorComponent,
  OperatorDetailComponent,
  OperatorUpdateComponent,
  OperatorDeletePopupComponent,
  OperatorDeleteDialogComponent,
  operatorRoute,
  operatorPopupRoute
} from './';

const ENTITY_STATES = [...operatorRoute, ...operatorPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OperatorComponent,
    OperatorDetailComponent,
    OperatorUpdateComponent,
    OperatorDeleteDialogComponent,
    OperatorDeletePopupComponent
  ],
  entryComponents: [OperatorComponent, OperatorUpdateComponent, OperatorDeleteDialogComponent, OperatorDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedOperatorModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
