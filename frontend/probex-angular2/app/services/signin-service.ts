import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class SigninService {

	serviceUrl: string = "http://localhost:8080/ProbexService/rest/signin";

	private user: User;

	error: string;

	constructor(private http: Http) {

	}

	signin(username: string, password: string) {
		this.validateAndGetUser(username, password).subscribe(
			data => {
				this.user = data;
				if (this.user != null) {
					console.log("succesful");
					sessionStorage['username'] = this.user.username
				}
			},
			error => {
				this.error = "Could not sign in";
				console.log("could not sign in");
			}
		);
	}

	signout() {
		delete sessionStorage['username'];
	}

	signedin() {
		return sessionStorage['username'] != null;
	}

	private validateAndGetUser(username: string, password: string) {
		let url = this.serviceUrl + '/u=' + username + '-p=' + password;
		return this.http.get(url).map(res => res.json());
	}
}