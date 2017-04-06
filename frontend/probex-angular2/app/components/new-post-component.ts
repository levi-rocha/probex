import { Component } from '@angular/core';
import { Post } from '.././models/post';
import { PostService } from '.././services/post-service';
import { OnInit } from '@angular/core';
import { Router, ROUTER_DIRECTIVES } from '@angular/router'; 

@Component({
	selector: 'new-post',
	templateUrl: 'app/views/new-post.html',
	providers: [ PostService ],
	directives: [ ROUTER_DIRECTIVES ]
})
export class NewPostComponent implements OnInit {

	private post: Post;

	error: string;

	constructor(private router: Router, private postService: PostService) {
	}

	ngOnInit() {
		this.post = new Post();
	}

	submit() {
		this.postService.insert(this.post).subscribe(
			data => this.router.navigate(['']),
			error => this.error = "Could not save post"
		);
	}
}