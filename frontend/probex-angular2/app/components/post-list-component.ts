import { Component } from '@angular/core';
import { Post } from '.././models/post';
import { PostService } from '.././services/post-service';
import { OnInit } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
	selector: 'post-list',
	templateUrl: 'app/views/post-list.html',
	providers: [ PostService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class PostListComponent implements OnInit {

	private posts: Post[];

	error: string;

	constructor(private postService: PostService) {
	}

	ngOnInit() {
		this.listLatest();
	}

	listLatest() {
        this.postService.listLast(10).subscribe(
            data => this.posts = data,
            error => this.error = "Could not list users"
        );
    }

}