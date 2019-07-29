import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGongjijinHistory } from 'app/shared/model/gongjijin-history.model';

@Component({
  selector: 'jhi-gongjijin-history-detail',
  templateUrl: './gongjijin-history-detail.component.html'
})
export class GongjijinHistoryDetailComponent implements OnInit {
  gongjijinHistory: IGongjijinHistory;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gongjijinHistory }) => {
      this.gongjijinHistory = gongjijinHistory;
    });
  }

  previousState() {
    window.history.back();
  }
}
