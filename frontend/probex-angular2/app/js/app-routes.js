"use strict";
var router_1 = require('@angular/router');
var user_routes_1 = require('./components/users/user-routes');
var login_routes_1 = require('./components/autenticacao/login-routes');
var login_routes_2 = require('./components/autenticacao/login-routes');
exports.routes = user_routes_1.UserRoutes.concat(login_routes_1.LoginRoutes);
exports.APP_ROUTER_PROVIDERS = [
    router_1.provideRouter(exports.routes),
    login_routes_2.AUTH_PROVIDERS
];
//# sourceMappingURL=app-routes.js.map