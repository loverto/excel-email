import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMailConfig } from 'app/shared/model/mail-config.model';

@Component({
  selector: 'jhi-mail-config-detail',
  templateUrl: './mail-config-detail.component.html'
})
export class MailConfigDetailComponent implements OnInit {
  mailConfig: IMailConfig;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mailConfig }) => {
      this.mailConfig = mailConfig;
    });
  }

  previousState() {
    window.history.back();
  }
}
