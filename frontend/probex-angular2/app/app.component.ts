import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { SigninService } from './services/signin-service';

@Component({
  selector: 'meu-app',
  templateUrl: 'app/menu.html',
  providers: [ SigninService ],
  directives: [ROUTER_DIRECTIVES]
})
export class AppComponent {

  private loggedUsername: string;

  constructor(private signinService: SigninService) {
    this.signinService.loggedUser.subscribe(
      value => {
        this.loggedUsername = value;
        console.log("loggedUser changed: " + value);
      }, error => console.log("error")
    );
  }

}