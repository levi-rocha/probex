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
var core_1 = require('angular2/core');
var permission_1 = require("./model/permission");
var user_1 = require("./model/user");
var common_1 = require('angular2/common');
var email_validator_1 = require('./validators/email-validator');
var user_service_1 = require('./service/user-service');
var FormUserComponent = (function () {
    function FormUserComponent(fb, userService) {
        this.fb = fb;
        this.userService = userService;
        this.success = false;
        this.message = "";
    }
    FormUserComponent.prototype.ngOnInit = function () {
        this.user = new user_1.User();
        this.permissions = [
            new permission_1.Permission('standard', 'Standard'),
            new permission_1.Permission('enterprise', 'Enterprise'),
            new permission_1.Permission('admin', 'Administrator')
        ];
        this.buildForm(this.fb);
        this.editId = -1;
        this.listAll();
    };
    FormUserComponent.prototype.buildForm = function (fb) {
        this.userForm = fb.group({
            username: ['', common_1.Validators.required],
            password: ['', common_1.Validators.required],
            email: ['', common_1.Validators.compose([common_1.Validators.required, email_validator_1.EmailValidator.validate])],
            permission: ['', common_1.Validators.required]
        });
    };
    FormUserComponent.prototype.listAll = function () {
        var _this = this;
        this.userService.listAll().subscribe(function (data) { return _this.users = data; }, function (error) { return _this.error = "Could not list users"; });
    };
    FormUserComponent.prototype.insert = function () {
        var _this = this;
        this.userService.insert(this.user).subscribe(function (data) { return _this.message = data; }, function (error) { return _this.error = "Could not save user"; }, function () { return _this.listAll(); });
        this.user = new user_1.User();
    };
    FormUserComponent.prototype.edit = function (user) {
        this.editId = user.id;
        this.user = user;
    };
    FormUserComponent.prototype.update = function () {
        var _this = this;
        this.userService.update(this.user).subscribe(function (data) { return _this.message = data; }, function (error) { return _this.error = "Could not update user"; }, function () { return _this.listAll(); });
        this.user = new user_1.User();
        this.editId = -1;
    };
    FormUserComponent.prototype.delete = function (user) {
        var _this = this;
        this.userService.delete(user.id).subscribe(function (data) { return _this.message = data; }, function (error) { return _this.error = "Could not delete user"; }, function () { return _this.listAll(); });
    };
    FormUserComponent = __decorate([
        core_1.Component({
            selector: 'form-user',
            templateUrl: 'app/view/form-user.html',
            providers: [user_service_1.UserService]
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof common_1.FormBuilder !== 'undefined' && common_1.FormBuilder) === 'function' && _a) || Object, user_service_1.UserService])
    ], FormUserComponent);
    return FormUserComponent;
    var _a;
}());
exports.FormUserComponent = FormUserComponent;
//# sourceMappingURL=form-user-component.js.map