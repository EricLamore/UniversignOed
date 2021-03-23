import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UniversignOedSharedModule } from 'app/shared/shared.module';
import { ColumnComponent } from './column.component';
import { ColumnDetailComponent } from './column-detail.component';
import { ColumnUpdateComponent } from './column-update.component';
import { ColumnDeleteDialogComponent } from './column-delete-dialog.component';
import { columnRoute } from './column.route';

@NgModule({
  imports: [UniversignOedSharedModule, RouterModule.forChild(columnRoute)],
  declarations: [ColumnComponent, ColumnDetailComponent, ColumnUpdateComponent, ColumnDeleteDialogComponent],
  entryComponents: [ColumnDeleteDialogComponent],
})
export class UniversignOedColumnModule {}
