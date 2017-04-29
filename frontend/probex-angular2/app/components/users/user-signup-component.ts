import { Component } from '@angular/core';
import { User } from '../.././models/user';
import { UserService } from '../.././services/user-service';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'user-signup',
	templateUrl: 'app/views/users/signUp.html',
	providers: [ UserService ]
})
export class UserSignupComponent implements OnInit {

	private user: User;

	error: string;

	constructor(private router: Router, private userService: UserService) {
	}

	ngOnInit() {
		this.user = new User();
	}

	signUp() {
		this.userService.insert(this.user).subscribe(
			data => this.router.navigate(['/user-list']),
			error => this.error = "Could not save user"
		);
	}
}