import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupConfiguration } from 'app/shared/model/group-configuration.model';

@Component({
  selector: 'jhi-group-configuration-detail',
  templateUrl: './group-configuration-detail.component.html'
})
export class GroupConfigurationDetailComponent implements OnInit {
  groupConfiguration: IGroupConfiguration;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupConfiguration }) => {
      this.groupConfiguration = groupConfiguration;
    });
  }

  previousState() {
    window.history.back();
  }
}
