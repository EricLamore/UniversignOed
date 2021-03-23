import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { MapPropertiesComponent } from './map-properties.component';
import { MapPropertiesDetailComponent } from './map-properties-detail.component';
import { MapPropertiesUpdateComponent } from './map-properties-update.component';
import { MapPropertiesDeleteDialogComponent } from './map-properties-delete-dialog.component';
import { mapPropertiesRoute } from './map-properties.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(mapPropertiesRoute)],
  declarations: [MapPropertiesComponent, MapPropertiesDetailComponent, MapPropertiesUpdateComponent, MapPropertiesDeleteDialogComponent],
  entryComponents: [MapPropertiesDeleteDialogComponent],
})
export class UniversignOedMapPropertiesModule {}
