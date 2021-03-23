import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { LinkComponent } from './link.component';
import { LinkDetailComponent } from './link-detail.component';
import { LinkUpdateComponent } from './link-update.component';
import { LinkDeleteDialogComponent } from './link-delete-dialog.component';
import { linkRoute } from './link.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(linkRoute)],
  declarations: [LinkComponent, LinkDetailComponent, LinkUpdateComponent, LinkDeleteDialogComponent],
  entryComponents: [LinkDeleteDialogComponent],
})
export class UniversignOedLinkModule {}
