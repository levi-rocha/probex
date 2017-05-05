import {Component, ViewEncapsulation} from '@angular/core';
import { SigninService } from './services/signin-service';

@Component({
  selector: 'meu-app',
  templateUrl: 'app/menu.html',
  providers: [ SigninService ],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {

  private loggedUsername: string;
  private signedIn: boolean = this.loggedUsername != '' && this.loggedUsername != null;

  constructor(private signinService: SigninService) {
    this.signinService.loggedUser.subscribe(
      value => {
        this.loggedUsername = value;
        console.log("loggedUser changed: " + value);
      }, error => console.log("error")
    );
  }

}