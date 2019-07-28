/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { GongjijinHistoryDetailComponent } from 'app/entities/gongjijin-history/gongjijin-history-detail.component';
import { GongjijinHistory } from 'app/shared/model/gongjijin-history.model';

describe('Component Tests', () => {
  describe('GongjijinHistory Management Detail Component', () => {
    let comp: GongjijinHistoryDetailComponent;
    let fixture: ComponentFixture<GongjijinHistoryDetailComponent>;
    const route = ({ data: of({ gongjijinHistory: new GongjijinHistory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [GongjijinHistoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GongjijinHistoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GongjijinHistoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gongjijinHistory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
