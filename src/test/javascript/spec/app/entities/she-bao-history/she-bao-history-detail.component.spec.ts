/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { SheBaoHistoryDetailComponent } from 'app/entities/she-bao-history/she-bao-history-detail.component';
import { SheBaoHistory } from 'app/shared/model/she-bao-history.model';

describe('Component Tests', () => {
  describe('SheBaoHistory Management Detail Component', () => {
    let comp: SheBaoHistoryDetailComponent;
    let fixture: ComponentFixture<SheBaoHistoryDetailComponent>;
    const route = ({ data: of({ sheBaoHistory: new SheBaoHistory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [SheBaoHistoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SheBaoHistoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SheBaoHistoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sheBaoHistory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
