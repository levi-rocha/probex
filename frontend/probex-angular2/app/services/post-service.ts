import { Injectable } from '@angular/core';
import { Post } from '../models/post';
import {Http, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class PostService {
    serviceUrl: string = "http://localhost:8080/ProbexService/rest/posts";
    constructor(private http: Http) {}

    listLast(number: number) {
        let url = this.serviceUrl + '/q=' + number;
        return this.http.get(this.serviceUrl).map(res => res.json());
    }

    insert(post: Post) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(post);
        return this.http.post(this.serviceUrl, body, options).map(res => res.text());
    }
}