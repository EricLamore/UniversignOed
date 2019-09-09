import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperator } from 'app/shared/model/operator.model';

@Component({
  selector: 'jhi-operator-detail',
  templateUrl: './operator-detail.component.html'
})
export class OperatorDetailComponent implements OnInit {
  operator: IOperator;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ operator }) => {
      this.operator = operator;
    });
  }

  previousState() {
    window.history.back();
  }
}
