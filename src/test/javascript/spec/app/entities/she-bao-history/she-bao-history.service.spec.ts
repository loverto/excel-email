/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { SheBaoHistoryService } from 'app/entities/she-bao-history/she-bao-history.service';
import { ISheBaoHistory, SheBaoHistory } from 'app/shared/model/she-bao-history.model';

describe('Service Tests', () => {
  describe('SheBaoHistory Service', () => {
    let injector: TestBed;
    let service: SheBaoHistoryService;
    let httpMock: HttpTestingController;
    let elemDefault: ISheBaoHistory;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SheBaoHistoryService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SheBaoHistory(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a SheBaoHistory', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new SheBaoHistory(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a SheBaoHistory', async () => {
        const returnedFromService = Object.assign(
          {
            xh: 1,
            dept: 'BBBBBB',
            name: 'BBBBBB',
            sbjsYanglaoShiye: 'BBBBBB',
            sbjsGongShang: 'BBBBBB',
            sbjsYiShangSheng: 'BBBBBB',
            grkkxxYaolang: 'BBBBBB',
            grkkxxShiye: 'BBBBBB',
            grkkxxYiliao: 'BBBBBB',
            grkkxxTotal: 'BBBBBB',
            dwbfYaolao: 'BBBBBB',
            dwbfShiye: 'BBBBBB',
            dwbfYiliao: 'BBBBBB',
            dwbfGongshang: 'BBBBBB',
            dwbfShengyu: 'BBBBBB',
            dwbfTotal: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of SheBaoHistory', async () => {
        const returnedFromService = Object.assign(
          {
            xh: 1,
            dept: 'BBBBBB',
            name: 'BBBBBB',
            sbjsYanglaoShiye: 'BBBBBB',
            sbjsGongShang: 'BBBBBB',
            sbjsYiShangSheng: 'BBBBBB',
            grkkxxYaolang: 'BBBBBB',
            grkkxxShiye: 'BBBBBB',
            grkkxxYiliao: 'BBBBBB',
            grkkxxTotal: 'BBBBBB',
            dwbfYaolao: 'BBBBBB',
            dwbfShiye: 'BBBBBB',
            dwbfYiliao: 'BBBBBB',
            dwbfGongshang: 'BBBBBB',
            dwbfShengyu: 'BBBBBB',
            dwbfTotal: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SheBaoHistory', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
