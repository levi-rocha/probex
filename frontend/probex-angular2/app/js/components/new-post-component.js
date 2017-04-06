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
var core_1 = require('@angular/core');
var post_1 = require('.././models/post');
var post_service_1 = require('.././services/post-service');
var router_1 = require('@angular/router');
var NewPostComponent = (function () {
    function NewPostComponent(router, postService) {
        this.router = router;
        this.postService = postService;
    }
    NewPostComponent.prototype.ngOnInit = function () {
        this.post = new post_1.Post();
    };
    NewPostComponent.prototype.submit = function () {
        var _this = this;
        this.postService.insert(this.post).subscribe(function (data) { return _this.router.navigate(['']); }, function (error) { return _this.error = "Could not save post"; });
    };
    NewPostComponent = __decorate([
        core_1.Component({
            selector: 'new-post',
            templateUrl: 'app/views/new-post.html',
            providers: [post_service_1.PostService],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [router_1.Router, post_service_1.PostService])
    ], NewPostComponent);
    return NewPostComponent;
}());
exports.NewPostComponent = NewPostComponent;
//# sourceMappingURL=new-post-component.js.map