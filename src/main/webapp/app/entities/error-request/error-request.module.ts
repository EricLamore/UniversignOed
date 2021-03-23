import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { ErrorRequestComponent } from './error-request.component';
import { ErrorRequestDetailComponent } from './error-request-detail.component';
import { ErrorRequestUpdateComponent } from './error-request-update.component';
import { ErrorRequestDeleteDialogComponent } from './error-request-delete-dialog.component';
import { errorRequestRoute } from './error-request.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(errorRequestRoute)],
  declarations: [ErrorRequestComponent, ErrorRequestDetailComponent, ErrorRequestUpdateComponent, ErrorRequestDeleteDialogComponent],
  entryComponents: [ErrorRequestDeleteDialogComponent],
})
export class UniversignOedErrorRequestModule {}
