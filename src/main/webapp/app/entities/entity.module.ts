import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'gongjijin',
        loadChildren: './gongjijin/gongjijin.module#ExcelEmailGongjijinModule'
      },
      {
        path: 'user-info',
        loadChildren: './user-info/user-info.module#ExcelEmailUserInfoModule'
      },
      {
        path: 'mail-config',
        loadChildren: './mail-config/mail-config.module#ExcelEmailMailConfigModule'
      },
      {
        path: 'she-bao',
        loadChildren: './she-bao/she-bao.module#ExcelEmailSheBaoModule'
      },
      {
        path: 'mail-content',
        loadChildren: './mail-content/mail-content.module#ExcelEmailMailContentModule'
      },
      {
        path: 'gongjijin-history',
        loadChildren: './gongjijin-history/gongjijin-history.module#ExcelEmailGongjijinHistoryModule'
      },
      {
        path: 'she-bao-history',
        loadChildren: './she-bao-history/she-bao-history.module#ExcelEmailSheBaoHistoryModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExcelEmailEntityModule {}
