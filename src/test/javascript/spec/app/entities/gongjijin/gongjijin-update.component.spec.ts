/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { GongjijinUpdateComponent } from 'app/entities/gongjijin/gongjijin-update.component';
import { GongjijinService } from 'app/entities/gongjijin/gongjijin.service';
import { Gongjijin } from 'app/shared/model/gongjijin.model';

describe('Component Tests', () => {
  describe('Gongjijin Management Update Component', () => {
    let comp: GongjijinUpdateComponent;
    let fixture: ComponentFixture<GongjijinUpdateComponent>;
    let service: GongjijinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [GongjijinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GongjijinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GongjijinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GongjijinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Gongjijin(123);
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
        const entity = new Gongjijin();
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
