export interface ICertifiedUser {
  id?: string;
  firstname?: string;
  lastname?: string;
  email?: string;
  phoneNumber?: string;
}

export class CertifiedUser implements ICertifiedUser {
  constructor(
    public id?: string,
    public firstname?: string,
    public lastname?: string,
    public email?: string,
    public phoneNumber?: string
  ) {}
}
