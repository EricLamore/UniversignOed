import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapProperties } from 'app/shared/model/map-properties.model';

@Component({
  selector: 'jhi-map-properties-detail',
  templateUrl: './map-properties-detail.component.html'
})
export class MapPropertiesDetailComponent implements OnInit {
  mapProperties: IMapProperties;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mapProperties }) => {
      this.mapProperties = mapProperties;
    });
  }

  previousState() {
    window.history.back();
  }
}
