/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { GongjijinHistoryUpdateComponent } from 'app/entities/gongjijin-history/gongjijin-history-update.component';
import { GongjijinHistoryService } from 'app/entities/gongjijin-history/gongjijin-history.service';
import { GongjijinHistory } from 'app/shared/model/gongjijin-history.model';

describe('Component Tests', () => {
  describe('GongjijinHistory Management Update Component', () => {
    let comp: GongjijinHistoryUpdateComponent;
    let fixture: ComponentFixture<GongjijinHistoryUpdateComponent>;
    let service: GongjijinHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [GongjijinHistoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GongjijinHistoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GongjijinHistoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GongjijinHistoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GongjijinHistory(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new GongjijinHistory();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
