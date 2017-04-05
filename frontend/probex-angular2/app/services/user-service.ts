import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class UserService {

    serviceUrl: string = "http://localhost:8080/ProbexService/rest/users";

    constructor(private http: Http) {

    }

    listAll() {
        return this.http.get(this.serviceUrl).map(res => res.json());
    }

    insert(user: User) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(user);
        return this.http.post(this.serviceUrl, body, options).map(res => res.text());
    }

    update(user: User) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(user);
        return this.http.put(this.serviceUrl, body, options).map(res => res.text());
    }

    delete(id: number) {
        let url = this.serviceUrl + '/' + id;
        return this.http.delete(url).map(res => res.text());
    }

    findById(id: number) {
        let url = this.serviceUrl + '/' + id;
        return this.http.get(url).map(res => res.json());
    }

}