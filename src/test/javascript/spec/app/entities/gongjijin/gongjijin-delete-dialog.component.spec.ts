/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExcelEmailTestModule } from '../../../test.module';
import { GongjijinDeleteDialogComponent } from 'app/entities/gongjijin/gongjijin-delete-dialog.component';
import { GongjijinService } from 'app/entities/gongjijin/gongjijin.service';

describe('Component Tests', () => {
  describe('Gongjijin Management Delete Component', () => {
    let comp: GongjijinDeleteDialogComponent;
    let fixture: ComponentFixture<GongjijinDeleteDialogComponent>;
    let service: GongjijinService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [GongjijinDeleteDialogComponent]
      })
        .overrideTemplate(GongjijinDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GongjijinDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GongjijinService);
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
