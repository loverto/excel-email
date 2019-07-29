import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGongjijinHistory, GongjijinHistory } from 'app/shared/model/gongjijin-history.model';
import { GongjijinHistoryService } from './gongjijin-history.service';

@Component({
  selector: 'jhi-gongjijin-history-update',
  templateUrl: './gongjijin-history-update.component.html'
})
export class GongjijinHistoryUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    xh: [],
    dept: [],
    name: [],
    gjjjs: [],
    grkkmx: [],
    dwkkmx: [],
    total: []
  });

  constructor(
    protected gongjijinHistoryService: GongjijinHistoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gongjijinHistory }) => {
      this.updateForm(gongjijinHistory);
    });
  }

  updateForm(gongjijinHistory: IGongjijinHistory) {
    this.editForm.patchValue({
      id: gongjijinHistory.id,
      xh: gongjijinHistory.xh,
      dept: gongjijinHistory.dept,
      name: gongjijinHistory.name,
      gjjjs: gongjijinHistory.gjjjs,
      grkkmx: gongjijinHistory.grkkmx,
      dwkkmx: gongjijinHistory.dwkkmx,
      total: gongjijinHistory.total
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gongjijinHistory = this.createFromForm();
    if (gongjijinHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.gongjijinHistoryService.update(gongjijinHistory));
    } else {
      this.subscribeToSaveResponse(this.gongjijinHistoryService.create(gongjijinHistory));
    }
  }

  private createFromForm(): IGongjijinHistory {
    return {
      ...new GongjijinHistory(),
      id: this.editForm.get(['id']).value,
      xh: this.editForm.get(['xh']).value,
      dept: this.editForm.get(['dept']).value,
      name: this.editForm.get(['name']).value,
      gjjjs: this.editForm.get(['gjjjs']).value,
      grkkmx: this.editForm.get(['grkkmx']).value,
      dwkkmx: this.editForm.get(['dwkkmx']).value,
      total: this.editForm.get(['total']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGongjijinHistory>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
