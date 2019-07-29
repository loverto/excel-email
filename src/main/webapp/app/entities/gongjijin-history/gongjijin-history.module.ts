import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ExcelEmailSharedModule } from 'app/shared';
import {
  GongjijinHistoryComponent,
  GongjijinHistoryDetailComponent,
  GongjijinHistoryUpdateComponent,
  GongjijinHistoryDeletePopupComponent,
  GongjijinHistoryDeleteDialogComponent,
  gongjijinHistoryRoute,
  gongjijinHistoryPopupRoute
} from './';

const ENTITY_STATES = [...gongjijinHistoryRoute, ...gongjijinHistoryPopupRoute];

@NgModule({
  imports: [ExcelEmailSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GongjijinHistoryComponent,
    GongjijinHistoryDetailComponent,
    GongjijinHistoryUpdateComponent,
    GongjijinHistoryDeleteDialogComponent,
    GongjijinHistoryDeletePopupComponent
  ],
  entryComponents: [
    GongjijinHistoryComponent,
    GongjijinHistoryUpdateComponent,
    GongjijinHistoryDeleteDialogComponent,
    GongjijinHistoryDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailGongjijinHistoryModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
