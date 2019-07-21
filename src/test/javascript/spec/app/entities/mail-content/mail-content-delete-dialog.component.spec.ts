/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExcelEmailTestModule } from '../../../test.module';
import { MailContentDeleteDialogComponent } from 'app/entities/mail-content/mail-content-delete-dialog.component';
import { MailContentService } from 'app/entities/mail-content/mail-content.service';

describe('Component Tests', () => {
  describe('MailContent Management Delete Component', () => {
    let comp: MailContentDeleteDialogComponent;
    let fixture: ComponentFixture<MailContentDeleteDialogComponent>;
    let service: MailContentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [MailContentDeleteDialogComponent]
      })
        .overrideTemplate(MailContentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MailContentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MailContentService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
