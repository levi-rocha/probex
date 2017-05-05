import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable, BehaviorSubject } from 'rxjs/Rx';
import {Router} from "@angular/router";

@Injectable()
export class SigninService {

	serviceUrl: string = "http://localhost:8080/ProbexService/rest/signin";

	private user: User;

	error: string;

	loggedUser = new BehaviorSubject("");
	private router: Router;

	constructor(private http: Http) {

	}

	signIn(username: string, password: string) {
		this.validateAndGetUser(username, password).subscribe(
			data => {
				this.user = data;
				if (this.user != null) {
					console.log("succesful");
					sessionStorage['username'] = this.user.username
					this.loggedUser.next(this.user.username);
				}
			},
			error => {
				this.error = "Could not sign in";
				console.log("could not sign in");
			}
		);
	}

	signOut() {
		delete sessionStorage['username'];
		this.loggedUser.next("");
		this.router.navigate(['/signIn']);
	}

	static signedIn() {
		return sessionStorage['username'] != null;
	}

	private validateAndGetUser(username: string, password: string) {
		let params: URLSearchParams  = new URLSearchParams();
		params.set("username", username);
		params.set("password", password);
		return this.http.get(this.serviceUrl, {search: params})
			.map(res => res.json());
	}
}