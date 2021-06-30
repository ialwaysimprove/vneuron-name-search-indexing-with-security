import { Component } from '@angular/core';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'ngx-form-elements',
  template: `
    <router-outlet></router-outlet>
  `,
})
export class WatchlistSearchComponent {

  constructor(
    private keycloakService: KeycloakService,
  ) {
  }

}
