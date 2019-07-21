export interface IMailContent {
  id?: number;
  mailSubject?: string;
  mailContent?: string;
}

export class MailContent implements IMailContent {
  constructor(public id?: number, public mailSubject?: string, public mailContent?: string) {}
}
