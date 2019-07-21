export interface IUserInfo {
  id?: number;
  name?: string;
  mail?: string;
  internetMail?: string;
  weiXin?: string;
  qq?: string;
  phone?: string;
  idCard?: string;
}

export class UserInfo implements IUserInfo {
  constructor(
    public id?: number,
    public name?: string,
    public mail?: string,
    public internetMail?: string,
    public weiXin?: string,
    public qq?: string,
    public phone?: string,
    public idCard?: string
  ) {}
}
