import { Component, OnInit } from '@angular/core';
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

	constructor(private router: Router, private signinService: SigninService) {
	}

	signin() {
		console.log("signin called with " + this.username + " and " + this.password);
		this.signinService.signin(this.username, this.password);
		this.router.navigate(['']);
	}
}