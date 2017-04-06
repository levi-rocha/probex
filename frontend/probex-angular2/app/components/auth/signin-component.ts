import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SigninService } from '../../services/signin-service';

@Component({
	selector: 'signin',
	templateUrl: 'app/views/signin.html',
	providers: [ SigninService ]
})
export class SigninComponent {

	private username: string;
	private password: string;

	error: string;

	constructor(private router: Router, private signinService: SigninService) {
		this.signinService.loggedUser.subscribe(
			value => {
				console.log(value);
				if (value != "") {
					this.router.navigate(['']);
				}
			},
			error => {
				this.error = "Could not log in";
			}
		);
	}

	signin() {
		this.signinService.signin(this.username, this.password);	
	}
}