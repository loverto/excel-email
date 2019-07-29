import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGongjijinHistory } from 'app/shared/model/gongjijin-history.model';
import { GongjijinHistoryService } from './gongjijin-history.service';

@Component({
  selector: 'jhi-gongjijin-history-delete-dialog',
  templateUrl: './gongjijin-history-delete-dialog.component.html'
})
export class GongjijinHistoryDeleteDialogComponent {
  gongjijinHistory: IGongjijinHistory;

  constructor(
    protected gongjijinHistoryService: GongjijinHistoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gongjijinHistoryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'gongjijinHistoryListModification',
        content: 'Deleted an gongjijinHistory'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gongjijin-history-delete-popup',
  template: ''
})
export class GongjijinHistoryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gongjijinHistory }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GongjijinHistoryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gongjijinHistory = gongjijinHistory;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/gongjijin-history', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/gongjijin-history', { outlets: { popup: null } }]);
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
