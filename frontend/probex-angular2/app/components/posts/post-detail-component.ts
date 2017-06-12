import {Component, OnInit} from "@angular/core";
import {PostService} from "../../services/post-service";
import {ActivatedRoute} from "@angular/router";
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

    private post: Post;
    private newComment: string;

    constructor(
        private route: ActivatedRoute,
        private postService: PostService,
        public snackBar: MdSnackBar) {
    }

    ngOnInit() {
        this.reloadPost();
    }

    private reloadPost() {
        this.post = new Post();
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
                this.reloadPost();
            },
            error => this.snackBar.open("Erro ao enviar comentário", "OK")
        )
    }

    voteOnPost() {
        this.postService.addVote(sessionStorage['username'], this.post.id).subscribe(
          data => this.reloadPost(),
            error => this.snackBar.open('Erro:' + error, "OK")
        );
    }

    getVotes(): number {
        if (this.post.voteIds != null)
            return this.post.voteIds.length;
        return 0;
    }
}