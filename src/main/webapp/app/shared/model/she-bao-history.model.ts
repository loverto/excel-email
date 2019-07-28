export interface ISheBaoHistory {
  id?: number;
  xh?: number;
  dept?: string;
  name?: string;
  sbjsYanglaoShiye?: string;
  sbjsGongShang?: string;
  sbjsYiShangSheng?: string;
  grkkxxYaolang?: string;
  grkkxxShiye?: string;
  grkkxxYiliao?: string;
  grkkxxTotal?: string;
  dwbfYaolao?: string;
  dwbfShiye?: string;
  dwbfYiliao?: string;
  dwbfGongshang?: string;
  dwbfShengyu?: string;
  dwbfTotal?: string;
}

export class SheBaoHistory implements ISheBaoHistory {
  constructor(
    public id?: number,
    public xh?: number,
    public dept?: string,
    public name?: string,
    public sbjsYanglaoShiye?: string,
    public sbjsGongShang?: string,
    public sbjsYiShangSheng?: string,
    public grkkxxYaolang?: string,
    public grkkxxShiye?: string,
    public grkkxxYiliao?: string,
    public grkkxxTotal?: string,
    public dwbfYaolao?: string,
    public dwbfShiye?: string,
    public dwbfYiliao?: string,
    public dwbfGongshang?: string,
    public dwbfShengyu?: string,
    public dwbfTotal?: string
  ) {}
}
