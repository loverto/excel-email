import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ExcelEmailSharedModule } from 'app/shared';
import {
  MailContentComponent,
  MailContentDetailComponent,
  MailContentUpdateComponent,
  MailContentDeletePopupComponent,
  MailContentDeleteDialogComponent,
  mailContentRoute,
  mailContentPopupRoute
} from './';

const ENTITY_STATES = [...mailContentRoute, ...mailContentPopupRoute];

@NgModule({
  imports: [ExcelEmailSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MailContentComponent,
    MailContentDetailComponent,
    MailContentUpdateComponent,
    MailContentDeleteDialogComponent,
    MailContentDeletePopupComponent
  ],
  entryComponents: [MailContentComponent, MailContentUpdateComponent, MailContentDeleteDialogComponent, MailContentDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailMailContentModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
