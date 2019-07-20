/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { MailConfigDetailComponent } from 'app/entities/mail-config/mail-config-detail.component';
import { MailConfig } from 'app/shared/model/mail-config.model';

describe('Component Tests', () => {
  describe('MailConfig Management Detail Component', () => {
    let comp: MailConfigDetailComponent;
    let fixture: ComponentFixture<MailConfigDetailComponent>;
    const route = ({ data: of({ mailConfig: new MailConfig(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [MailConfigDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MailConfigDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MailConfigDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mailConfig).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
