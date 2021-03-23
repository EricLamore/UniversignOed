import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'group',
        loadChildren: () => import('./group/group.module').then(m => m.UniversignOedGroupModule),
      },
      {
        path: 'header-configuration',
        loadChildren: () =>
          import('./header-configuration/header-configuration.module').then(m => m.UniversignOedHeaderConfigurationModule),
      },
      {
        path: 'properties',
        loadChildren: () => import('./properties/properties.module').then(m => m.UniversignOedPropertiesModule),
      },
      {
        path: 'group-configuration',
        loadChildren: () => import('./group-configuration/group-configuration.module').then(m => m.UniversignOedGroupConfigurationModule),
      },
      {
        path: 'group-permission',
        loadChildren: () => import('./group-permission/group-permission.module').then(m => m.UniversignOedGroupPermissionModule),
      },
      {
        path: 'map-properties',
        loadChildren: () => import('./map-properties/map-properties.module').then(m => m.UniversignOedMapPropertiesModule),
      },
      {
        path: 'operator',
        loadChildren: () => import('./operator/operator.module').then(m => m.UniversignOedOperatorModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class UniversignOedEntityModule {}
