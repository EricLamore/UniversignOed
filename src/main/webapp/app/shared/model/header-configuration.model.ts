export interface IHeaderConfiguration {
  id?: string;
  logo?: string;
  width?: number;
  href?: string;
}

export class HeaderConfiguration implements IHeaderConfiguration {
  constructor(public id?: string, public logo?: string, public width?: number, public href?: string) {}
}
