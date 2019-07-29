/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExcelEmailTestModule } from '../../../test.module';
import { GongjijinHistoryDeleteDialogComponent } from 'app/entities/gongjijin-history/gongjijin-history-delete-dialog.component';
import { GongjijinHistoryService } from 'app/entities/gongjijin-history/gongjijin-history.service';

describe('Component Tests', () => {
  describe('GongjijinHistory Management Delete Component', () => {
    let comp: GongjijinHistoryDeleteDialogComponent;
    let fixture: ComponentFixture<GongjijinHistoryDeleteDialogComponent>;
    let service: GongjijinHistoryService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [GongjijinHistoryDeleteDialogComponent]
      })
        .overrideTemplate(GongjijinHistoryDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GongjijinHistoryDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GongjijinHistoryService);
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
