import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGongjijin } from 'app/shared/model/gongjijin.model';
import { GongjijinService } from './gongjijin.service';

@Component({
  selector: 'jhi-gongjijin-delete-dialog',
  templateUrl: './gongjijin-delete-dialog.component.html'
})
export class GongjijinDeleteDialogComponent {
  gongjijin: IGongjijin;

  constructor(protected gongjijinService: GongjijinService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gongjijinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'gongjijinListModification',
        content: 'Deleted an gongjijin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gongjijin-delete-popup',
  template: ''
})
export class GongjijinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gongjijin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GongjijinDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gongjijin = gongjijin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/gongjijin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/gongjijin', { outlets: { popup: null } }]);
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
