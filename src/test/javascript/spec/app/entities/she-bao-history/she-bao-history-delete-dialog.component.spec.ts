/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExcelEmailTestModule } from '../../../test.module';
import { SheBaoHistoryDeleteDialogComponent } from 'app/entities/she-bao-history/she-bao-history-delete-dialog.component';
import { SheBaoHistoryService } from 'app/entities/she-bao-history/she-bao-history.service';

describe('Component Tests', () => {
  describe('SheBaoHistory Management Delete Component', () => {
    let comp: SheBaoHistoryDeleteDialogComponent;
    let fixture: ComponentFixture<SheBaoHistoryDeleteDialogComponent>;
    let service: SheBaoHistoryService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [SheBaoHistoryDeleteDialogComponent]
      })
        .overrideTemplate(SheBaoHistoryDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SheBaoHistoryDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SheBaoHistoryService);
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
