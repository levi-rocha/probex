import { Component } from '@angular/core';
import { User } from '../.././models/user';
import { UserService } from '../.././services/user-service';
import { OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES, ActivatedRoute } from '@angular/router';

@Component({
	selector: 'user-view',
	templateUrl: 'app/views/users/view.html',
	providers: [ UserService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class UserViewComponent implements OnInit {

	private id: number;
	private user: User;

	error: string;

	constructor(
		private route: ActivatedRoute, 
		private userService: UserService) {
	}

	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.userService.findById(this.id).subscribe(
            data => this.user = data,
            error => this.error = "Could not find user"
        );
	}
}