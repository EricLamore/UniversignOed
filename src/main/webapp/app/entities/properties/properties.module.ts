import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { PropertiesComponent } from './properties.component';
import { PropertiesDetailComponent } from './properties-detail.component';
import { PropertiesUpdateComponent } from './properties-update.component';
import { PropertiesDeleteDialogComponent } from './properties-delete-dialog.component';
import { propertiesRoute } from './properties.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(propertiesRoute)],
  declarations: [PropertiesComponent, PropertiesDetailComponent, PropertiesUpdateComponent, PropertiesDeleteDialogComponent],
  entryComponents: [PropertiesDeleteDialogComponent],
})
export class UniversignOedPropertiesModule {}
