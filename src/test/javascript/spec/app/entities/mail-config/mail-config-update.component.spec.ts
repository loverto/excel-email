/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { ExcelEmailTestModule } from '../../../test.module';
import { MailConfigUpdateComponent } from 'app/entities/mail-config/mail-config-update.component';
import { MailConfigService } from 'app/entities/mail-config/mail-config.service';
import { MailConfig } from 'app/shared/model/mail-config.model';

describe('Component Tests', () => {
  describe('MailConfig Management Update Component', () => {
    let comp: MailConfigUpdateComponent;
    let fixture: ComponentFixture<MailConfigUpdateComponent>;
    let service: MailConfigService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [MailConfigUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MailConfigUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MailConfigUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MailConfigService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MailConfig(123);
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
        const entity = new MailConfig();
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
