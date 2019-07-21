import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMailContent } from 'app/shared/model/mail-content.model';
import { MailContentService } from './mail-content.service';

@Component({
  selector: 'jhi-mail-content-delete-dialog',
  templateUrl: './mail-content-delete-dialog.component.html'
})
export class MailContentDeleteDialogComponent {
  mailContent: IMailContent;

  constructor(
    protected mailContentService: MailContentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mailContentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mailContentListModification',
        content: 'Deleted an mailContent'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mail-content-delete-popup',
  template: ''
})
export class MailContentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mailContent }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MailContentDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mailContent = mailContent;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/mail-content', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/mail-content', { outlets: { popup: null } }]);
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
