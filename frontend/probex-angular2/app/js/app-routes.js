"use strict";
var router_1 = require('@angular/router');
var user_routes_1 = require('./components/users/user-routes');
var signin_routes_1 = require('./components/auth/signin-routes');
var post_routes_1 = require('./components/post-routes');
var signin_routes_2 = require('./components/auth/signin-routes');
exports.routes = user_routes_1.UserRoutes.concat(signin_routes_1.SigninRoutes, post_routes_1.PostRoutes);
exports.APP_ROUTER_PROVIDERS = [
    router_1.provideRouter(exports.routes),
    signin_routes_2.AUTH_PROVIDERS
];
//# sourceMappingURL=app-routes.js.map