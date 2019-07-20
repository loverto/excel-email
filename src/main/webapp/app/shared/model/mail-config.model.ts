export interface IMailConfig {
  id?: number;
  username?: string;
  password?: string;
  smtpServer?: string;
  smtpPort?: number;
  typeId?: string;
  mailType?: string;
}

export class MailConfig implements IMailConfig {
  constructor(
    public id?: number,
    public username?: string,
    public password?: string,
    public smtpServer?: string,
    public smtpPort?: number,
    public typeId?: string,
    public mailType?: string
  ) {}
}
