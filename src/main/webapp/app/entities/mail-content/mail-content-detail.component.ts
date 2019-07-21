import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMailContent } from 'app/shared/model/mail-content.model';

@Component({
  selector: 'jhi-mail-content-detail',
  templateUrl: './mail-content-detail.component.html'
})
export class MailContentDetailComponent implements OnInit {
  mailContent: IMailContent;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mailContent }) => {
      this.mailContent = mailContent;
    });
  }

  previousState() {
    window.history.back();
  }
}
