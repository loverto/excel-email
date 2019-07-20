export interface IGongjijin {
  id?: number;
  xh?: string;
  dept?: string;
  name?: string;
  gjjjs?: string;
  grkkmx?: string;
  dwkkmx?: string;
  total?: string;
}

export class Gongjijin implements IGongjijin {
  constructor(
    public id?: number,
    public xh?: string,
    public dept?: string,
    public name?: string,
    public gjjjs?: string,
    public grkkmx?: string,
    public dwkkmx?: string,
    public total?: string
  ) {}
}
