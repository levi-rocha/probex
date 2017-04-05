import { Component } from '@angular/core';
import { User } from '../.././models/user';
import { UserService } from '../.././services/user-service';
import { OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
	selector: 'user-list',
	templateUrl: 'app/views/users/list.html',
	providers: [ UserService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class UserListComponent implements OnInit {

	private users: User[];

	error: string;

	constructor(private userService: UserService) {
	}

	ngOnInit() {
		this.listAll();
	}

	listAll() {
        this.userService.listAll().subscribe(
            data => this.users = data,
            error => this.error = "Could not list users"
        );
    }

	delete(id: number) {
		this.userService.delete(id).subscribe(
			data => this.listAll(),
			error => this.error = "Could not delete user"
		);
	}
}