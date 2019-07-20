import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISheBao, SheBao } from 'app/shared/model/she-bao.model';
import { SheBaoService } from './she-bao.service';

@Component({
  selector: 'jhi-she-bao-update',
  templateUrl: './she-bao-update.component.html'
})
export class SheBaoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    xh: [],
    dept: [],
    name: [],
    sbjsYanglaoShiye: [],
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

  constructor(protected sheBaoService: SheBaoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sheBao }) => {
      this.updateForm(sheBao);
    });
  }

  updateForm(sheBao: ISheBao) {
    this.editForm.patchValue({
      id: sheBao.id,
      xh: sheBao.xh,
      dept: sheBao.dept,
      name: sheBao.name,
      sbjsYanglaoShiye: sheBao.sbjsYanglaoShiye,
      sbjsYiShangSheng: sheBao.sbjsYiShangSheng,
      grkkxxYaolang: sheBao.grkkxxYaolang,
      grkkxxShiye: sheBao.grkkxxShiye,
      grkkxxYiliao: sheBao.grkkxxYiliao,
      grkkxxTotal: sheBao.grkkxxTotal,
      dwbfYaolao: sheBao.dwbfYaolao,
      dwbfShiye: sheBao.dwbfShiye,
      dwbfYiliao: sheBao.dwbfYiliao,
      dwbfGongshang: sheBao.dwbfGongshang,
      dwbfShengyu: sheBao.dwbfShengyu,
      dwbfTotal: sheBao.dwbfTotal
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sheBao = this.createFromForm();
    if (sheBao.id !== undefined) {
      this.subscribeToSaveResponse(this.sheBaoService.update(sheBao));
    } else {
      this.subscribeToSaveResponse(this.sheBaoService.create(sheBao));
    }
  }

  private createFromForm(): ISheBao {
    return {
      ...new SheBao(),
      id: this.editForm.get(['id']).value,
      xh: this.editForm.get(['xh']).value,
      dept: this.editForm.get(['dept']).value,
      name: this.editForm.get(['name']).value,
      sbjsYanglaoShiye: this.editForm.get(['sbjsYanglaoShiye']).value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISheBao>>) {
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
