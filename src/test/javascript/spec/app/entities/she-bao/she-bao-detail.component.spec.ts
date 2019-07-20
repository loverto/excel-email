/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { SheBaoDetailComponent } from 'app/entities/she-bao/she-bao-detail.component';
import { SheBao } from 'app/shared/model/she-bao.model';

describe('Component Tests', () => {
  describe('SheBao Management Detail Component', () => {
    let comp: SheBaoDetailComponent;
    let fixture: ComponentFixture<SheBaoDetailComponent>;
    const route = ({ data: of({ sheBao: new SheBao(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [SheBaoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SheBaoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SheBaoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sheBao).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
