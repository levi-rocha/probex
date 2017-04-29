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
var user_1 = require('../.././models/user');
var user_service_1 = require('../.././services/user-service');
var router_1 = require('@angular/router');
var UserSignupComponent = (function () {
    function UserSignupComponent(router, userService) {
        this.router = router;
        this.userService = userService;
    }
    UserSignupComponent.prototype.ngOnInit = function () {
        this.user = new user_1.User();
    };
    UserSignupComponent.prototype.signUp = function () {
        var _this = this;
        this.userService.insert(this.user).subscribe(function (data) { return _this.router.navigate(['/user-list']); }, function (error) { return _this.error = "Could not save user"; });
    };
    UserSignupComponent = __decorate([
        core_1.Component({
            selector: 'user-signup',
            templateUrl: 'app/views/users/signUp.html',
            providers: [user_service_1.UserService],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [router_1.Router, user_service_1.UserService])
    ], UserSignupComponent);
    return UserSignupComponent;
}());
exports.UserSignupComponent = UserSignupComponent;
//# sourceMappingURL=user-signUp-component.js.map