"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Post = (function () {
    function Post(id, title, content, author, authorUsername, voteCount, comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.authorUsername = authorUsername;
        this.voteCount = voteCount;
        this.comments = comments;
    }
    return Post;
}());
exports.Post = Post;
//# sourceMappingURL=post.js.map