import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ExcelEmailSharedModule } from 'app/shared';
import {
  SheBaoComponent,
  SheBaoDetailComponent,
  SheBaoUpdateComponent,
  SheBaoDeletePopupComponent,
  SheBaoDeleteDialogComponent,
  sheBaoRoute,
  sheBaoPopupRoute
} from './';

const ENTITY_STATES = [...sheBaoRoute, ...sheBaoPopupRoute];

@NgModule({
  imports: [ExcelEmailSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [SheBaoComponent, SheBaoDetailComponent, SheBaoUpdateComponent, SheBaoDeleteDialogComponent, SheBaoDeletePopupComponent],
  entryComponents: [SheBaoComponent, SheBaoUpdateComponent, SheBaoDeleteDialogComponent, SheBaoDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailSheBaoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
