import { Component } from '@angular/core';
import { User } from '../.././models/user';
import { UserService } from '../.././services/user-service';
import { OnInit } from '@angular/core';
import { Router, ROUTER_DIRECTIVES } from '@angular/router'; 

@Component({
	selector: 'user-signup',
	templateUrl: 'app/views/users/signup.html',
	providers: [ UserService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class UserSignupComponent implements OnInit {

	private user: User;

	error: string;

	constructor(private router: Router, private userService: UserService) {
	}

	ngOnInit() {
		this.user = new User();
	}

	signup() {
		this.userService.insert(this.user).subscribe(
			data => this.router.navigate(['/user-list']),
			error => this.error = "Could not save user"
		);
	}
}