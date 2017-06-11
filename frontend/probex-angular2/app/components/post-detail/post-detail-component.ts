import {Component, OnInit} from "@angular/core";
import {PostService} from "../../services/post-service";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "util";
import {Post} from "../../models/post";
import {MdSnackBar} from "@angular/material";
import {PostComment} from "../../models/post-comment";
import {AppComponent} from "../../app.component";
import {User} from "../../models/user";
@Component({
    selector: 'post-detail',
    templateUrl: 'app/views/post/detail.html',
    providers: [PostService, MdSnackBar]
})
export class PostDetailComponent implements OnInit {
    private post: Post = new Post;
    private newComment: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private postService: PostService,
        public snackBar: MdSnackBar) {
    }

    ngOnInit(): void {
        this.reloadPost();
    }

    private reloadPost() {
        this.post.id = +this.route.snapshot.params['id'];
        this.postService.get(this.post.id).subscribe(
            data => this.post = data,
            error => this.snackBar.open("Não foi possível carregar o post", "OK")
        );
    }

    submitNewComment(): void {
        let comment = new PostComment();
        comment.content = this.newComment;
        comment.author = new User();
        comment.author.username = AppComponent.loggedUsername();
        comment.post = new Post();
        comment.post.id = this.post.id;
        this.postService.addComment(comment).subscribe(
            data => {
                this.newComment = null;
                this.snackBar.open("Comentário incluído com sucesso", "OK");
                this.reloadPost();
            },
            error => this.snackBar.open("Erro ao enviar comentário", "OK")
        )
    }
}