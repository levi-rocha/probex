System.register([], function(exports_1) {
    var User;
    return {
        setters:[],
        execute: function() {
            User = (function () {
                function User(id, username, password, email, permission) {
                    this.id = id;
                    this.username = username;
                    this.password = password;
                    this.email = email;
                    this.permission = permission;
                }
                return User;
            })();
            exports_1("User", User);
        }
    }
});
//# sourceMappingURL=user.js.map