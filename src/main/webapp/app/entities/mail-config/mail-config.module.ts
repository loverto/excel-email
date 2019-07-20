import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ExcelEmailSharedModule } from 'app/shared';
import {
  MailConfigComponent,
  MailConfigDetailComponent,
  MailConfigUpdateComponent,
  MailConfigDeletePopupComponent,
  MailConfigDeleteDialogComponent,
  mailConfigRoute,
  mailConfigPopupRoute
} from './';

const ENTITY_STATES = [...mailConfigRoute, ...mailConfigPopupRoute];

@NgModule({
  imports: [ExcelEmailSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MailConfigComponent,
    MailConfigDetailComponent,
    MailConfigUpdateComponent,
    MailConfigDeleteDialogComponent,
    MailConfigDeletePopupComponent
  ],
  entryComponents: [MailConfigComponent, MailConfigUpdateComponent, MailConfigDeleteDialogComponent, MailConfigDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailMailConfigModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
