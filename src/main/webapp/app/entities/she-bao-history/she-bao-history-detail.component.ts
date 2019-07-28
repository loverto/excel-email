import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISheBaoHistory } from 'app/shared/model/she-bao-history.model';

@Component({
  selector: 'jhi-she-bao-history-detail',
  templateUrl: './she-bao-history-detail.component.html'
})
export class SheBaoHistoryDetailComponent implements OnInit {
  sheBaoHistory: ISheBaoHistory;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sheBaoHistory }) => {
      this.sheBaoHistory = sheBaoHistory;
    });
  }

  previousState() {
    window.history.back();
  }
}
