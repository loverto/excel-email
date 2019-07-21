import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMailContent, MailContent } from 'app/shared/model/mail-content.model';
import { MailContentService } from './mail-content.service';

@Component({
  selector: 'jhi-mail-content-update',
  templateUrl: './mail-content-update.component.html'
})
export class MailContentUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    mailSubject: [],
    mailContent: []
  });

  constructor(protected mailContentService: MailContentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mailContent }) => {
      this.updateForm(mailContent);
    });
  }

  updateForm(mailContent: IMailContent) {
    this.editForm.patchValue({
      id: mailContent.id,
      mailSubject: mailContent.mailSubject,
      mailContent: mailContent.mailContent
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mailContent = this.createFromForm();
    if (mailContent.id !== undefined) {
      this.subscribeToSaveResponse(this.mailContentService.update(mailContent));
    } else {
      this.subscribeToSaveResponse(this.mailContentService.create(mailContent));
    }
  }

  private createFromForm(): IMailContent {
    return {
      ...new MailContent(),
      id: this.editForm.get(['id']).value,
      mailSubject: this.editForm.get(['mailSubject']).value,
      mailContent: this.editForm.get(['mailContent']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMailContent>>) {
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
