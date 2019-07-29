import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISheBaoHistory } from 'app/shared/model/she-bao-history.model';
import { SheBaoHistoryService } from './she-bao-history.service';

@Component({
  selector: 'jhi-she-bao-history-delete-dialog',
  templateUrl: './she-bao-history-delete-dialog.component.html'
})
export class SheBaoHistoryDeleteDialogComponent {
  sheBaoHistory: ISheBaoHistory;

  constructor(
    protected sheBaoHistoryService: SheBaoHistoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sheBaoHistoryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sheBaoHistoryListModification',
        content: 'Deleted an sheBaoHistory'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-she-bao-history-delete-popup',
  template: ''
})
export class SheBaoHistoryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sheBaoHistory }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SheBaoHistoryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sheBaoHistory = sheBaoHistory;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/she-bao-history', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/she-bao-history', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
