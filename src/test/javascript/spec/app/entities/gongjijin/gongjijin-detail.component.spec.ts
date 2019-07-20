/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { GongjijinDetailComponent } from 'app/entities/gongjijin/gongjijin-detail.component';
import { Gongjijin } from 'app/shared/model/gongjijin.model';

describe('Component Tests', () => {
  describe('Gongjijin Management Detail Component', () => {
    let comp: GongjijinDetailComponent;
    let fixture: ComponentFixture<GongjijinDetailComponent>;
    const route = ({ data: of({ gongjijin: new Gongjijin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [GongjijinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GongjijinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GongjijinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gongjijin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
