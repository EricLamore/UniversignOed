import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { GroupConfigurationComponent } from './group-configuration.component';
import { GroupConfigurationDetailComponent } from './group-configuration-detail.component';
import { GroupConfigurationUpdateComponent } from './group-configuration-update.component';
import { GroupConfigurationDeleteDialogComponent } from './group-configuration-delete-dialog.component';
import { groupConfigurationRoute } from './group-configuration.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(groupConfigurationRoute)],
  declarations: [
    GroupConfigurationComponent,
    GroupConfigurationDetailComponent,
    GroupConfigurationUpdateComponent,
    GroupConfigurationDeleteDialogComponent,
  ],
  entryComponents: [GroupConfigurationDeleteDialogComponent],
})
export class UniversignOedGroupConfigurationModule {}
