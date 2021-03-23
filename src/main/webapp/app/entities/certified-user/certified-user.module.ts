import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { CertifiedUserComponent } from './certified-user.component';
import { CertifiedUserDetailComponent } from './certified-user-detail.component';
import { CertifiedUserUpdateComponent } from './certified-user-update.component';
import { CertifiedUserDeleteDialogComponent } from './certified-user-delete-dialog.component';
import { certifiedUserRoute } from './certified-user.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(certifiedUserRoute)],
  declarations: [CertifiedUserComponent, CertifiedUserDetailComponent, CertifiedUserUpdateComponent, CertifiedUserDeleteDialogComponent],
  entryComponents: [CertifiedUserDeleteDialogComponent],
})
export class UniversignOedCertifiedUserModule {}
