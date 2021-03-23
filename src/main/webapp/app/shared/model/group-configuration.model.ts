import { IHeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { IProperties } from 'app/shared/model/properties.model';
import { IMapProperties } from 'app/shared/model/map-properties.model';

export interface IGroupConfiguration {
  id?: string;
  header?: IHeaderConfiguration;
  i18n?: IProperties;
  metaDatas?: IProperties;
  languages?: IMapProperties;
}

export class GroupConfiguration implements IGroupConfiguration {
  constructor(
    public id?: string,
    public header?: IHeaderConfiguration,
    public i18n?: IProperties,
    public metaDatas?: IProperties,
    public languages?: IMapProperties
  ) {}
}
