"use strict";
var user_list_component_1 = require('./user-list-component');
var user_signup_component_1 = require('./user-signup-component');
var user_edit_component_1 = require('./user-edit-component');
var user_view_component_1 = require('./user-view-component');
var login_guard_1 = require('../../login-guard');
exports.UserRoutes = [
    {
        path: 'user-list',
        component: user_list_component_1.UserListComponent,
        canActivate: [login_guard_1.LoginGuard]
    },
    {
        path: 'user-signup',
        component: user_signup_component_1.UserSignupComponent,
        canActivate: [login_guard_1.LoginGuard]
    },
    {
        path: 'user-edit/:id',
        component: user_edit_component_1.UserEditComponent,
        canActivate: [login_guard_1.LoginGuard]
    },
    {
        path: 'user-view/:id',
        component: user_view_component_1.UserViewComponent,
        canActivate: [login_guard_1.LoginGuard]
    },
    {
        path: '',
        redirectTo: '/user-list',
        terminal: true
    }
];
//# sourceMappingURL=user-routes.js.map