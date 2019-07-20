import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGongjijin, Gongjijin } from 'app/shared/model/gongjijin.model';
import { GongjijinService } from './gongjijin.service';

@Component({
  selector: 'jhi-gongjijin-update',
  templateUrl: './gongjijin-update.component.html'
})
export class GongjijinUpdateComponent implements OnInit {
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

  constructor(protected gongjijinService: GongjijinService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gongjijin }) => {
      this.updateForm(gongjijin);
    });
  }

  updateForm(gongjijin: IGongjijin) {
    this.editForm.patchValue({
      id: gongjijin.id,
      xh: gongjijin.xh,
      dept: gongjijin.dept,
      name: gongjijin.name,
      gjjjs: gongjijin.gjjjs,
      grkkmx: gongjijin.grkkmx,
      dwkkmx: gongjijin.dwkkmx,
      total: gongjijin.total
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gongjijin = this.createFromForm();
    if (gongjijin.id !== undefined) {
      this.subscribeToSaveResponse(this.gongjijinService.update(gongjijin));
    } else {
      this.subscribeToSaveResponse(this.gongjijinService.create(gongjijin));
    }
  }

  private createFromForm(): IGongjijin {
    return {
      ...new Gongjijin(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGongjijin>>) {
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
