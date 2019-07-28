import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ExcelEmailSharedModule } from 'app/shared';
import {
  SheBaoHistoryComponent,
  SheBaoHistoryDetailComponent,
  SheBaoHistoryUpdateComponent,
  SheBaoHistoryDeletePopupComponent,
  SheBaoHistoryDeleteDialogComponent,
  sheBaoHistoryRoute,
  sheBaoHistoryPopupRoute
} from './';

const ENTITY_STATES = [...sheBaoHistoryRoute, ...sheBaoHistoryPopupRoute];

@NgModule({
  imports: [ExcelEmailSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SheBaoHistoryComponent,
    SheBaoHistoryDetailComponent,
    SheBaoHistoryUpdateComponent,
    SheBaoHistoryDeleteDialogComponent,
    SheBaoHistoryDeletePopupComponent
  ],
  entryComponents: [
    SheBaoHistoryComponent,
    SheBaoHistoryUpdateComponent,
    SheBaoHistoryDeleteDialogComponent,
    SheBaoHistoryDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailSheBaoHistoryModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
