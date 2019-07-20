/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExcelEmailTestModule } from '../../../test.module';
import { SheBaoDeleteDialogComponent } from 'app/entities/she-bao/she-bao-delete-dialog.component';
import { SheBaoService } from 'app/entities/she-bao/she-bao.service';

describe('Component Tests', () => {
  describe('SheBao Management Delete Component', () => {
    let comp: SheBaoDeleteDialogComponent;
    let fixture: ComponentFixture<SheBaoDeleteDialogComponent>;
    let service: SheBaoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ExcelEmailTestModule],
        declarations: [SheBaoDeleteDialogComponent]
      })
        .overrideTemplate(SheBaoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SheBaoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SheBaoService);
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
