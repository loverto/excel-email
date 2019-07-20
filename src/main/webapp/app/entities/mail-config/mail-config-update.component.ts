import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMailConfig, MailConfig } from 'app/shared/model/mail-config.model';
import { MailConfigService } from './mail-config.service';

@Component({
  selector: 'jhi-mail-config-update',
  templateUrl: './mail-config-update.component.html'
})
export class MailConfigUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    username: [],
    password: [],
    smtpServer: [],
    smtpPort: [],
    typeId: [],
    mailType: []
  });

  constructor(protected mailConfigService: MailConfigService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mailConfig }) => {
      this.updateForm(mailConfig);
    });
  }

  updateForm(mailConfig: IMailConfig) {
    this.editForm.patchValue({
      id: mailConfig.id,
      username: mailConfig.username,
      password: mailConfig.password,
      smtpServer: mailConfig.smtpServer,
      smtpPort: mailConfig.smtpPort,
      typeId: mailConfig.typeId,
      mailType: mailConfig.mailType
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mailConfig = this.createFromForm();
    if (mailConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.mailConfigService.update(mailConfig));
    } else {
      this.subscribeToSaveResponse(this.mailConfigService.create(mailConfig));
    }
  }

  private createFromForm(): IMailConfig {
    return {
      ...new MailConfig(),
      id: this.editForm.get(['id']).value,
      username: this.editForm.get(['username']).value,
      password: this.editForm.get(['password']).value,
      smtpServer: this.editForm.get(['smtpServer']).value,
      smtpPort: this.editForm.get(['smtpPort']).value,
      typeId: this.editForm.get(['typeId']).value,
      mailType: this.editForm.get(['mailType']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMailConfig>>) {
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
