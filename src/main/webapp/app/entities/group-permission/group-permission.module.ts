import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { GroupPermissionComponent } from './group-permission.component';
import { GroupPermissionDetailComponent } from './group-permission-detail.component';
import { GroupPermissionUpdateComponent } from './group-permission-update.component';
import { GroupPermissionDeleteDialogComponent } from './group-permission-delete-dialog.component';
import { groupPermissionRoute } from './group-permission.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(groupPermissionRoute)],
  declarations: [
    GroupPermissionComponent,
    GroupPermissionDetailComponent,
    GroupPermissionUpdateComponent,
    GroupPermissionDeleteDialogComponent,
  ],
  entryComponents: [GroupPermissionDeleteDialogComponent],
})
export class UniversignOedGroupPermissionModule {}
