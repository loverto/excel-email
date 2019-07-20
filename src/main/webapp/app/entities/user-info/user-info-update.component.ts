import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IUserInfo, UserInfo } from 'app/shared/model/user-info.model';
import { UserInfoService } from './user-info.service';

@Component({
  selector: 'jhi-user-info-update',
  templateUrl: './user-info-update.component.html'
})
export class UserInfoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    mail: [],
    internetMail: [],
    weiXin: [],
    qq: [],
    phone: []
  });

  constructor(protected userInfoService: UserInfoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userInfo }) => {
      this.updateForm(userInfo);
    });
  }

  updateForm(userInfo: IUserInfo) {
    this.editForm.patchValue({
      id: userInfo.id,
      name: userInfo.name,
      mail: userInfo.mail,
      internetMail: userInfo.internetMail,
      weiXin: userInfo.weiXin,
      qq: userInfo.qq,
      phone: userInfo.phone
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const userInfo = this.createFromForm();
    if (userInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.userInfoService.update(userInfo));
    } else {
      this.subscribeToSaveResponse(this.userInfoService.create(userInfo));
    }
  }

  private createFromForm(): IUserInfo {
    return {
      ...new UserInfo(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      mail: this.editForm.get(['mail']).value,
      internetMail: this.editForm.get(['internetMail']).value,
      weiXin: this.editForm.get(['weiXin']).value,
      qq: this.editForm.get(['qq']).value,
      phone: this.editForm.get(['phone']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserInfo>>) {
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
