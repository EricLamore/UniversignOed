import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILink, Link } from 'app/shared/model/link.model';
import { LinkService } from './link.service';
import { IGroup } from 'app/shared/model/group.model';
import { GroupService } from 'app/entities/group/group.service';

@Component({
  selector: 'jhi-link-update',
  templateUrl: './link-update.component.html',
})
export class LinkUpdateComponent implements OnInit {
  isSaving = false;
  groups: IGroup[] = [];

  editForm = this.fb.group({
    id: [],
    group: [],
    group: [],
    group: [],
  });

  constructor(
    protected linkService: LinkService,
    protected groupService: GroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ link }) => {
      this.updateForm(link);

      this.groupService.query().subscribe((res: HttpResponse<IGroup[]>) => (this.groups = res.body || []));
    });
  }

  updateForm(link: ILink): void {
    this.editForm.patchValue({
      id: link.id,
      group: link.group,
      group: link.group,
      group: link.group,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const link = this.createFromForm();
    if (link.id !== undefined) {
      this.subscribeToSaveResponse(this.linkService.update(link));
    } else {
      this.subscribeToSaveResponse(this.linkService.create(link));
    }
  }

  private createFromForm(): ILink {
    return {
      ...new Link(),
      id: this.editForm.get(['id'])!.value,
      group: this.editForm.get(['group'])!.value,
      group: this.editForm.get(['group'])!.value,
      group: this.editForm.get(['group'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILink>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IGroup): any {
    return item.id;
  }
}
