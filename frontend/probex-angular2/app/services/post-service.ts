import {Injectable} from '@angular/core';
import {Post} from '../models/post';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {PostComment} from "../models/post-comment";

@Injectable()
export class PostService {
    serviceUrl: string = "http://localhost:8080/ProbexService/rest/posts";
    commentsUrl: string = "http://localhost:8080/ProbexService/rest/comments";

    constructor(private http: Http) {
    }

    listLast(number: number) {
        let url = this.serviceUrl + '/q=' + number;
        return this.http.get(this.serviceUrl).map(res => res.json());
    }

    insert(post: Post): Observable<Post> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        let body = JSON.stringify(post);
        return this.http
            .post(this.serviceUrl, body, options)
            .map((res) => res.json())
            .catch((error:any) => Observable.throw(error._body));
    }

    get(id: number) {
        let url = this.serviceUrl + '/' + id;
        return this.http.get(url).map(res => res.json());
    }

    addComment(comment: PostComment) {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        let body = JSON.stringify(comment);
        return this.http.post(this.commentsUrl, body, options).map(res => res.text());
    }

    addVote(username: string, postId: number) {
        let url = this.serviceUrl + '/vote';
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        let body = JSON.stringify({username: username, postId: postId});
        return this.http
            .post(url, body, options)
            .map((res) => res.json())
            .catch((error:any) => Observable.throw(error._body));
    }
}