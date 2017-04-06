"use strict";
var signin_component_1 = require('./signin-component');
var signout_component_1 = require('./signout-component');
var auth_guard_1 = require('../../auth-guard');
var signin_service_1 = require('../../services/signin-service');
exports.SigninRoutes = [
    { path: 'signin', component: signin_component_1.SigninComponent },
    { path: 'signout', component: signout_component_1.SignoutComponent }
];
exports.AUTH_PROVIDERS = [auth_guard_1.AuthGuard, signin_service_1.SigninService];
//# sourceMappingURL=signin-routes.js.map