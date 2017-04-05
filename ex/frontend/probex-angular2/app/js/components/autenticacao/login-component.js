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
var router_1 = require('@angular/router');
var login_service_1 = require('../../services/login-service');
var LoginComponent = (function () {
    function LoginComponent(router, loginService) {
        this.router = router;
        this.loginService = loginService;
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.usuario = 'admin';
        this.senha = 'admin';
    };
    LoginComponent.prototype.logar = function () {
        this.loginService.logar();
        this.router.navigate(['']);
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'login',
            template: "\n\t\t<h1>Login</h1>\n\t\t<div class=\"well\">\n\t\t    <form role=\"form\">\n\t\t        <div class=\"form-group\">\n\t\t            <label class=\"control-label\" for=\"usuario\">Usu\u00E1rio</label>\n\t\t            <input type=\"text\" class=\"form-control\" name=\"usuario\" \n\t\t                id=\"usuario\" [(ngModel)]=\"usuario\">\n\t\t        </div>\n\t\t        <div class=\"form-group\">\n\t\t            <label class=\"control-label\" for=\"senha\">Senha</label>\n\t\t            <input type=\"password\" class=\"form-control\" name=\"senha\" \n\t\t                id=\"senha\" [(ngModel)]=\"senha\">\n\t\t        </div>\n\t\t        <div class=\"form-group text-center\">\n\t\t            <button class=\"btn btn-success\" (click)=\"logar()\">\n\t\t                Entrar\n\t\t            </button>\n\t\t        </div>\n\t\t    </form>\n\t\t</div>",
            providers: [login_service_1.LoginService]
        }), 
        __metadata('design:paramtypes', [router_1.Router, login_service_1.LoginService])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login-component.js.map