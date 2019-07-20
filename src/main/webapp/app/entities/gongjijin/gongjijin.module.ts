import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ExcelEmailSharedModule } from 'app/shared';
import {
  GongjijinComponent,
  GongjijinDetailComponent,
  GongjijinUpdateComponent,
  GongjijinDeletePopupComponent,
  GongjijinDeleteDialogComponent,
  gongjijinRoute,
  gongjijinPopupRoute
} from './';

const ENTITY_STATES = [...gongjijinRoute, ...gongjijinPopupRoute];

@NgModule({
  imports: [ExcelEmailSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GongjijinComponent,
    GongjijinDetailComponent,
    GongjijinUpdateComponent,
    GongjijinDeleteDialogComponent,
    GongjijinDeletePopupComponent
  ],
  entryComponents: [GongjijinComponent, GongjijinUpdateComponent, GongjijinDeleteDialogComponent, GongjijinDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailGongjijinModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
