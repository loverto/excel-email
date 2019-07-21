/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { MailContentUpdateComponent } from 'app/entities/mail-content/mail-content-update.component';
import { MailContentService } from 'app/entities/mail-content/mail-content.service';
import { MailContent } from 'app/shared/model/mail-content.model';

describe('Component Tests', () => {
  describe('MailContent Management Update Component', () => {
    let comp: MailContentUpdateComponent;
    let fixture: ComponentFixture<MailContentUpdateComponent>;
    let service: MailContentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [MailContentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MailContentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MailContentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MailContentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MailContent(123);
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
        const entity = new MailContent();
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
