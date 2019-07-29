export interface IGongjijinHistory {
  id?: number;
  xh?: string;
  dept?: string;
  name?: string;
  gjjjs?: string;
  grkkmx?: string;
  dwkkmx?: string;
  total?: string;
}

export class GongjijinHistory implements IGongjijinHistory {
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
