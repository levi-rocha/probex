"use strict";
var User = (function () {
    function User(id, username, password, email, permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.permissions = permissions;
    }
    return User;
}());
exports.User = User;
//# sourceMappingURL=user.js.map