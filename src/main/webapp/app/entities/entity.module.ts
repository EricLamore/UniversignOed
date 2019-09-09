import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'administrator',
        loadChildren: () => import('./administrator/administrator.module').then(m => m.UniversignOedAdministratorModule)
      },
      {
        path: 'certified-user',
        loadChildren: () => import('./certified-user/certified-user.module').then(m => m.UniversignOedCertifiedUserModule)
      },
      {
        path: 'error-request',
        loadChildren: () => import('./error-request/error-request.module').then(m => m.UniversignOedErrorRequestModule)
      },
      {
        path: 'group',
        loadChildren: () => import('./group/group.module').then(m => m.UniversignOedGroupModule)
      },
      {
        path: 'link',
        loadChildren: () => import('./link/link.module').then(m => m.UniversignOedLinkModule)
      },
      {
        path: 'group-configuration',
        loadChildren: () => import('./group-configuration/group-configuration.module').then(m => m.UniversignOedGroupConfigurationModule)
      },
      {
        path: 'group-permission',
        loadChildren: () => import('./group-permission/group-permission.module').then(m => m.UniversignOedGroupPermissionModule)
      },
      {
        path: 'header-configuration',
        loadChildren: () => import('./header-configuration/header-configuration.module').then(m => m.UniversignOedHeaderConfigurationModule)
      },
      {
        path: 'map-properties',
        loadChildren: () => import('./map-properties/map-properties.module').then(m => m.UniversignOedMapPropertiesModule)
      },
      {
        path: 'operator',
        loadChildren: () => import('./operator/operator.module').then(m => m.UniversignOedOperatorModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UniversignOedEntityModule {}
