import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISheBao } from 'app/shared/model/she-bao.model';

@Component({
  selector: 'jhi-she-bao-detail',
  templateUrl: './she-bao-detail.component.html'
})
export class SheBaoDetailComponent implements OnInit {
  sheBao: ISheBao;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sheBao }) => {
      this.sheBao = sheBao;
    });
  }

  previousState() {
    window.history.back();
  }
}
