import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupPermission } from 'app/shared/model/group-permission.model';

@Component({
  selector: 'jhi-group-permission-detail',
  templateUrl: './group-permission-detail.component.html',
})
export class GroupPermissionDetailComponent implements OnInit {
  groupPermission: IGroupPermission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ groupPermission }) => (this.groupPermission = groupPermission));
  }

  previousState(): void {
    window.history.back();
  }
}
