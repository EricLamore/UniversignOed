import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { AdminPermissionsComponent } from './admin-permissions.component';
import { AdminPermissionsDetailComponent } from './admin-permissions-detail.component';
import { AdminPermissionsUpdateComponent } from './admin-permissions-update.component';
import { AdminPermissionsDeleteDialogComponent } from './admin-permissions-delete-dialog.component';
import { adminPermissionsRoute } from './admin-permissions.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(adminPermissionsRoute)],
  declarations: [
    AdminPermissionsComponent,
    AdminPermissionsDetailComponent,
    AdminPermissionsUpdateComponent,
    AdminPermissionsDeleteDialogComponent,
  ],
  entryComponents: [AdminPermissionsDeleteDialogComponent],
})
export class UniversignOedAdminPermissionsModule {}
