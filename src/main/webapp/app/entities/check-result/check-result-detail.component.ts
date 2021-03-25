import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICheckResult } from 'app/shared/model/check-result.model';

@Component({
  selector: 'jhi-check-result-detail',
  templateUrl: './check-result-detail.component.html',
})
export class CheckResultDetailComponent implements OnInit {
  checkResult: ICheckResult | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ checkResult }) => (this.checkResult = checkResult));
  }

  previousState(): void {
    window.history.back();
  }
}
