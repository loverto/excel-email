import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISheBaoHistory, SheBaoHistory } from 'app/shared/model/she-bao-history.model';
import { SheBaoHistoryService } from './she-bao-history.service';

@Component({
  selector: 'jhi-she-bao-history-update',
  templateUrl: './she-bao-history-update.component.html'
})
export class SheBaoHistoryUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    xh: [],
    dept: [],
    name: [],
    sbjsYanglaoShiye: [],
    sbjsGongShang: [],
    sbjsYiShangSheng: [],
    grkkxxYaolang: [],
    grkkxxShiye: [],
    grkkxxYiliao: [],
    grkkxxTotal: [],
    dwbfYaolao: [],
    dwbfShiye: [],
    dwbfYiliao: [],
    dwbfGongshang: [],
    dwbfShengyu: [],
    dwbfTotal: []
  });

  constructor(protected sheBaoHistoryService: SheBaoHistoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sheBaoHistory }) => {
      this.updateForm(sheBaoHistory);
    });
  }

  updateForm(sheBaoHistory: ISheBaoHistory) {
    this.editForm.patchValue({
      id: sheBaoHistory.id,
      xh: sheBaoHistory.xh,
      dept: sheBaoHistory.dept,
      name: sheBaoHistory.name,
      sbjsYanglaoShiye: sheBaoHistory.sbjsYanglaoShiye,
      sbjsGongShang: sheBaoHistory.sbjsGongShang,
      sbjsYiShangSheng: sheBaoHistory.sbjsYiShangSheng,
      grkkxxYaolang: sheBaoHistory.grkkxxYaolang,
      grkkxxShiye: sheBaoHistory.grkkxxShiye,
      grkkxxYiliao: sheBaoHistory.grkkxxYiliao,
      grkkxxTotal: sheBaoHistory.grkkxxTotal,
      dwbfYaolao: sheBaoHistory.dwbfYaolao,
      dwbfShiye: sheBaoHistory.dwbfShiye,
      dwbfYiliao: sheBaoHistory.dwbfYiliao,
      dwbfGongshang: sheBaoHistory.dwbfGongshang,
      dwbfShengyu: sheBaoHistory.dwbfShengyu,
      dwbfTotal: sheBaoHistory.dwbfTotal
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sheBaoHistory = this.createFromForm();
    if (sheBaoHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.sheBaoHistoryService.update(sheBaoHistory));
    } else {
      this.subscribeToSaveResponse(this.sheBaoHistoryService.create(sheBaoHistory));
    }
  }

  private createFromForm(): ISheBaoHistory {
    return {
      ...new SheBaoHistory(),
      id: this.editForm.get(['id']).value,
      xh: this.editForm.get(['xh']).value,
      dept: this.editForm.get(['dept']).value,
      name: this.editForm.get(['name']).value,
      sbjsYanglaoShiye: this.editForm.get(['sbjsYanglaoShiye']).value,
      sbjsGongShang: this.editForm.get(['sbjsGongShang']).value,
      sbjsYiShangSheng: this.editForm.get(['sbjsYiShangSheng']).value,
      grkkxxYaolang: this.editForm.get(['grkkxxYaolang']).value,
      grkkxxShiye: this.editForm.get(['grkkxxShiye']).value,
      grkkxxYiliao: this.editForm.get(['grkkxxYiliao']).value,
      grkkxxTotal: this.editForm.get(['grkkxxTotal']).value,
      dwbfYaolao: this.editForm.get(['dwbfYaolao']).value,
      dwbfShiye: this.editForm.get(['dwbfShiye']).value,
      dwbfYiliao: this.editForm.get(['dwbfYiliao']).value,
      dwbfGongshang: this.editForm.get(['dwbfGongshang']).value,
      dwbfShengyu: this.editForm.get(['dwbfShengyu']).value,
      dwbfTotal: this.editForm.get(['dwbfTotal']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISheBaoHistory>>) {
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
