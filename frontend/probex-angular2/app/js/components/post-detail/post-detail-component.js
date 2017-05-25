"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var post_service_1 = require("../../services/post-service");
var router_1 = require("@angular/router");
var post_1 = require("../../models/post");
var material_1 = require("@angular/material");
var PostDetailComponent = (function () {
    function PostDetailComponent(route, router, postService, snackBar) {
        this.route = route;
        this.router = router;
        this.postService = postService;
        this.snackBar = snackBar;
        this.post = new post_1.Post;
    }
    PostDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.post.id = +this.route.snapshot.params['id'];
        this.postService.get(this.post.id).subscribe(function (data) { return _this.post = data; }, function (error) { return _this.snackBar.open("Não foi possível carregar o post", "OK"); });
    };
    return PostDetailComponent;
}());
PostDetailComponent = __decorate([
    core_1.Component({
        selector: 'post-detail',
        templateUrl: 'app/views/post/detail.html',
        providers: [post_service_1.PostService, material_1.MdSnackBar]
    }),
    __metadata("design:paramtypes", [router_1.ActivatedRoute,
        router_1.Router,
        post_service_1.PostService,
        material_1.MdSnackBar])
], PostDetailComponent);
exports.PostDetailComponent = PostDetailComponent;
//# sourceMappingURL=post-detail-component.js.map