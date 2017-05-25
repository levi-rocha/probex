import {Component, OnInit} from "@angular/core";
import {PostService} from "../../services/post-service";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "util";
import {Post} from "../../models/post";
import {MdSnackBar} from "@angular/material";
@Component({
    selector: 'post-detail',
    templateUrl: 'app/views/post/detail.html',
    providers: [PostService, MdSnackBar]
})
export class PostDetailComponent implements OnInit {
    private post: Post = new Post;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private postService: PostService,
        public snackBar: MdSnackBar) {
    }

    ngOnInit(): void {
        this.post.id =  +this.route.snapshot.params['id'];
        this.postService.get(this.post.id).subscribe(
            data => this.post = data,
            error => this.snackBar.open("Não foi possível carregar o post", "OK")
        );
    }
}