/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { SheBaoHistoryUpdateComponent } from 'app/entities/she-bao-history/she-bao-history-update.component';
import { SheBaoHistoryService } from 'app/entities/she-bao-history/she-bao-history.service';
import { SheBaoHistory } from 'app/shared/model/she-bao-history.model';

describe('Component Tests', () => {
  describe('SheBaoHistory Management Update Component', () => {
    let comp: SheBaoHistoryUpdateComponent;
    let fixture: ComponentFixture<SheBaoHistoryUpdateComponent>;
    let service: SheBaoHistoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [SheBaoHistoryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SheBaoHistoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SheBaoHistoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SheBaoHistoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SheBaoHistory(123);
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
        const entity = new SheBaoHistory();
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
