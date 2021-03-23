import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { CheckResultComponent } from './check-result.component';
import { CheckResultDetailComponent } from './check-result-detail.component';
import { CheckResultUpdateComponent } from './check-result-update.component';
import { CheckResultDeleteDialogComponent } from './check-result-delete-dialog.component';
import { checkResultRoute } from './check-result.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(checkResultRoute)],
  declarations: [CheckResultComponent, CheckResultDetailComponent, CheckResultUpdateComponent, CheckResultDeleteDialogComponent],
  entryComponents: [CheckResultDeleteDialogComponent],
})
export class UniversignOedCheckResultModule {}
