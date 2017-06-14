import {Injectable} from '@angular/core';
import {Post} from '../models/post';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {PostComment} from "../models/post-comment";
import {Solution} from "../models/solution";

@Injectable()
export class PostService {
    serviceUrl: string = "http://localhost:8080/ProbexService/rest/posts";
    commentsUrl: string = "http://localhost:8080/ProbexService/rest/comments";
    solutionsUrl: string = "http://localhost:8080/ProbexService/rest/solutions";

    public static POPULAR: string = "&c=popular";
    public static LATEST: string = "";

    constructor(private http: Http) {
    }

    list(quantity: number, start: number, criteria: string) {
        let url = this.serviceUrl + "?q=" + quantity + "&s=" + start + criteria;
        return this.http.get(url).map(res => res.json());
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

    addSolution(solution: Solution) {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        let body = JSON.stringify(solution);
        return this.http.post(this.solutionsUrl, body, options).map(res => res.text());
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