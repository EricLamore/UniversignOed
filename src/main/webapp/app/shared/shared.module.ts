import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UniversignOedSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [UniversignOedSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [UniversignOedSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedSharedModule {
  static forRoot() {
    return {
      ngModule: UniversignOedSharedModule
    };
  }
}
