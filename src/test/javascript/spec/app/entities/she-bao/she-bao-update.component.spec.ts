/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { SheBaoUpdateComponent } from 'app/entities/she-bao/she-bao-update.component';
import { SheBaoService } from 'app/entities/she-bao/she-bao.service';
import { SheBao } from 'app/shared/model/she-bao.model';

describe('Component Tests', () => {
  describe('SheBao Management Update Component', () => {
    let comp: SheBaoUpdateComponent;
    let fixture: ComponentFixture<SheBaoUpdateComponent>;
    let service: SheBaoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [SheBaoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SheBaoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SheBaoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SheBaoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SheBao(123);
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
        const entity = new SheBao();
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
