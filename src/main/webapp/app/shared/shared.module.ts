import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ExcelEmailSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [ExcelEmailSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [ExcelEmailSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailSharedModule {
  static forRoot() {
    return {
      ngModule: ExcelEmailSharedModule
    };
  }
}
