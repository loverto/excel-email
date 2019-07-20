import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGongjijin } from 'app/shared/model/gongjijin.model';

@Component({
  selector: 'jhi-gongjijin-detail',
  templateUrl: './gongjijin-detail.component.html'
})
export class GongjijinDetailComponent implements OnInit {
  gongjijin: IGongjijin;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gongjijin }) => {
      this.gongjijin = gongjijin;
    });
  }

  previousState() {
    window.history.back();
  }
}
