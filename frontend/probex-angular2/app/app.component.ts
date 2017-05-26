import {Component, ViewEncapsulation} from '@angular/core';
import {SigninService} from './services/signin-service';

@Component({
    selector: 'meu-app',
    templateUrl: 'app/menu.html',
    providers: [SigninService],
    encapsulation: ViewEncapsulation.None
})
export class AppComponent {
    constructor(private signinService: SigninService) {
        // this.signinService.loggedUser.subscribe(
        //   value => {
        //     this.loggedUsername = value;
        //     console.log("loggedUser changed: " + value);
        //   }, error => console.log("error")
        // );
    }

    static isLogged(): boolean {
        return sessionStorage['username'] != null;
    }

    isLogged(): boolean {
        return AppComponent.isLogged();
    }

    static loggedUsername(): string {
        return sessionStorage['username'] || '';
    }

    loggedUsername(): string {
        return AppComponent.loggedUsername();
    }
}