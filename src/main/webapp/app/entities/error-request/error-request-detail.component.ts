import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IErrorRequest } from 'app/shared/model/error-request.model';

@Component({
  selector: 'jhi-error-request-detail',
  templateUrl: './error-request-detail.component.html'
})
export class ErrorRequestDetailComponent implements OnInit {
  errorRequest: IErrorRequest;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ errorRequest }) => {
      this.errorRequest = errorRequest;
    });
  }

  previousState() {
    window.history.back();
  }
}
