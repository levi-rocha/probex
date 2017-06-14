import {Component, OnInit} from "@angular/core";
import {Post} from "../../models/post";
import {PostService} from "../../services/post-service";

@Component({
	selector: 'post-list',
	templateUrl: 'app/views/post/post-list.html',
	providers: [PostService]
})
export class PostListComponent implements OnInit {

	private posts: Post[];

	error: string;

	private page: number = 0;

	private pageSize: number = 3;

	private criteria: string;

	constructor(private postService: PostService) {
	}

	ngOnInit() {
	    this.criteria = PostService.LATEST;
		this.refreshList();
	}

	refreshList() {
        this.postService.list(this.pageSize, this.pageSize*this.page, this.criteria).subscribe(
            data => this.posts = data,
            error => this.error = "Could not list posts"
        );
    }

	onTabChange($event: any) {
        if ($event.index == 1) {
            this.criteria = PostService.POPULAR;
        } else {
            this.criteria = PostService.LATEST;
        }
        this.refreshList();
	}

	getPostsLength(): number {
		if (this.posts != null)
			return this.posts.length;
		return 0;
	}

	firstPage() {
        this.page = 0;
        this.refreshList();
    }

    previousPage() {
        this.page--;
        this.refreshList();
    }

    nextPage() {
        this.page++;
        this.refreshList();
    }

}