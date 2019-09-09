import { IHeaderConfiguration } from 'app/shared/model/header-configuration.model';
import { IMapProperties } from 'app/shared/model/map-properties.model';

export interface IGroupConfiguration {
  id?: string;
  header?: IHeaderConfiguration;
  i18n?: IMapProperties;
  metaDatas?: IMapProperties;
}

export class GroupConfiguration implements IGroupConfiguration {
  constructor(public id?: string, public header?: IHeaderConfiguration, public i18n?: IMapProperties, public metaDatas?: IMapProperties) {}
}
