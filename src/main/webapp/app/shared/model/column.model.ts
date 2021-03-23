export interface IColumn {
  id?: string;
  show?: boolean;
  name?: string;
  type?: string;
}

export class Column implements IColumn {
  constructor(public id?: string, public show?: boolean, public name?: string, public type?: string) {
    this.show = this.show || false;
  }
}
