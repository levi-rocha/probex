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
var http_1 = require("@angular/http");
var PostService = (function () {
    function PostService(http) {
        this.http = http;
        this.serviceUrl = "http://localhost:8080/ProbexService/rest/posts";
    }
    PostService.prototype.listLast = function (number) {
        var url = this.serviceUrl + '/q=' + number;
        return this.http.get(this.serviceUrl).map(function (res) { return res.json(); });
    };
    PostService.prototype.insert = function (post) {
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        var body = JSON.stringify(post);
        return this.http.post(this.serviceUrl, body, options).map(function (res) { return res.text(); });
    };
    PostService.prototype.get = function (id) {
        var url = this.serviceUrl + '/' + id;
        return this.http.get(url).map(function (res) { return res.json(); });
    };
    return PostService;
}());
PostService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], PostService);
exports.PostService = PostService;
//# sourceMappingURL=post-service.js.map