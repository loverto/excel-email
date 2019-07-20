import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMailConfig } from 'app/shared/model/mail-config.model';
import { MailConfigService } from './mail-config.service';

@Component({
  selector: 'jhi-mail-config-delete-dialog',
  templateUrl: './mail-config-delete-dialog.component.html'
})
export class MailConfigDeleteDialogComponent {
  mailConfig: IMailConfig;

  constructor(
    protected mailConfigService: MailConfigService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mailConfigService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mailConfigListModification',
        content: 'Deleted an mailConfig'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mail-config-delete-popup',
  template: ''
})
export class MailConfigDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mailConfig }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MailConfigDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mailConfig = mailConfig;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/mail-config', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/mail-config', { outlets: { popup: null } }]);
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
