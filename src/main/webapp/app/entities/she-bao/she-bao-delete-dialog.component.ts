import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISheBao } from 'app/shared/model/she-bao.model';
import { SheBaoService } from './she-bao.service';

@Component({
  selector: 'jhi-she-bao-delete-dialog',
  templateUrl: './she-bao-delete-dialog.component.html'
})
export class SheBaoDeleteDialogComponent {
  sheBao: ISheBao;

  constructor(protected sheBaoService: SheBaoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sheBaoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'sheBaoListModification',
        content: 'Deleted an sheBao'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-she-bao-delete-popup',
  template: ''
})
export class SheBaoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sheBao }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SheBaoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sheBao = sheBao;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/she-bao', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/she-bao', { outlets: { popup: null } }]);
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
