import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { HeaderConfigurationComponent } from './header-configuration.component';
import { HeaderConfigurationDetailComponent } from './header-configuration-detail.component';
import { HeaderConfigurationUpdateComponent } from './header-configuration-update.component';
import { HeaderConfigurationDeleteDialogComponent } from './header-configuration-delete-dialog.component';
import { headerConfigurationRoute } from './header-configuration.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(headerConfigurationRoute)],
  declarations: [
    HeaderConfigurationComponent,
    HeaderConfigurationDetailComponent,
    HeaderConfigurationUpdateComponent,
    HeaderConfigurationDeleteDialogComponent,
  ],
  entryComponents: [HeaderConfigurationDeleteDialogComponent],
})
export class UniversignOedHeaderConfigurationModule {}
