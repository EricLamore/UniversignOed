import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupPermission } from 'app/shared/model/group-permission.model';

@Component({
  selector: 'jhi-group-permission-detail',
  templateUrl: './group-permission-detail.component.html'
})
export class GroupPermissionDetailComponent implements OnInit {
  groupPermission: IGroupPermission;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupPermission }) => {
      this.groupPermission = groupPermission;
    });
  }

  previousState() {
    window.history.back();
  }
}
