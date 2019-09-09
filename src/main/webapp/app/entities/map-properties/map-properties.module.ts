import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { UniversignOedSharedModule } from 'app/shared';
import {
  MapPropertiesComponent,
  MapPropertiesDetailComponent,
  MapPropertiesUpdateComponent,
  MapPropertiesDeletePopupComponent,
  MapPropertiesDeleteDialogComponent,
  mapPropertiesRoute,
  mapPropertiesPopupRoute
} from './';

const ENTITY_STATES = [...mapPropertiesRoute, ...mapPropertiesPopupRoute];

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MapPropertiesComponent,
    MapPropertiesDetailComponent,
    MapPropertiesUpdateComponent,
    MapPropertiesDeleteDialogComponent,
    MapPropertiesDeletePopupComponent
  ],
  entryComponents: [
    MapPropertiesComponent,
    MapPropertiesUpdateComponent,
    MapPropertiesDeleteDialogComponent,
    MapPropertiesDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedMapPropertiesModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
