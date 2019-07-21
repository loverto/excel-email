/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { MailContentDetailComponent } from 'app/entities/mail-content/mail-content-detail.component';
import { MailContent } from 'app/shared/model/mail-content.model';

describe('Component Tests', () => {
  describe('MailContent Management Detail Component', () => {
    let comp: MailContentDetailComponent;
    let fixture: ComponentFixture<MailContentDetailComponent>;
    const route = ({ data: of({ mailContent: new MailContent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [MailContentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MailContentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MailContentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mailContent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
