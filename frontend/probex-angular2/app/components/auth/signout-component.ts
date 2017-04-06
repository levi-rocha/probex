import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SigninService } from '../../services/signin-service';

@Component({
	selector: 'signout',
	template: '',
	providers: [ SigninService ]
})
export class SignoutComponent implements OnInit {

	constructor(private router: Router, private signinService: SigninService) {
	}

	ngOnInit() {
		this.signout();
	}

	signout() {
		this.signinService.signout();
		this.router.navigate(['/signin']);
	}
}