import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICertifiedUser } from 'app/shared/model/certified-user.model';

@Component({
  selector: 'jhi-certified-user-detail',
  templateUrl: './certified-user-detail.component.html'
})
export class CertifiedUserDetailComponent implements OnInit {
  certifiedUser: ICertifiedUser;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ certifiedUser }) => {
      this.certifiedUser = certifiedUser;
    });
  }

  previousState() {
    window.history.back();
  }
}
