System.register(['angular2/core', "./model/permission", "./model/user", 'angular2/common', './validators/email-validator', './service/user-service'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, permission_1, user_1, common_1, email_validator_1, user_service_1;
    var FormUserComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (permission_1_1) {
                permission_1 = permission_1_1;
            },
            function (user_1_1) {
                user_1 = user_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (email_validator_1_1) {
                email_validator_1 = email_validator_1_1;
            },
            function (user_service_1_1) {
                user_service_1 = user_service_1_1;
            }],
        execute: function() {
            FormUserComponent = (function () {
                function FormUserComponent(fb, userService) {
                    this.fb = fb;
                    this.userService = userService;
                    this.success = false;
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
                    this.users = this.userService.listAll();
                };
                FormUserComponent.prototype.buildForm = function (fb) {
                    this.userForm = fb.group({
                        username: ['', common_1.Validators.required],
                        password: ['', common_1.Validators.required],
                        email: ['', common_1.Validators.compose([common_1.Validators.required, email_validator_1.EmailValidator.validate])],
                        permission: ['', common_1.Validators.required]
                    });
                };
                FormUserComponent.prototype.insert = function () {
                    this.userService.insert(this.user);
                    this.user = new user_1.User();
                };
                FormUserComponent.prototype.edit = function (id) {
                    this.editId = id;
                    this.user = new user_1.User(this.users[id].username, this.users[id].password, this.users[id].email, this.users[id].permission);
                };
                FormUserComponent.prototype.update = function () {
                    this.userService.update(this.editId, this.user);
                    this.user = new user_1.User();
                    this.editId = -1;
                };
                FormUserComponent.prototype.delete = function (id) {
                    this.userService.delete(id);
                    this.editId = -1;
                };
                FormUserComponent = __decorate([
                    core_1.Component({
                        selector: 'form-user',
                        templateUrl: 'app/view/form-user.html',
                        providers: [user_service_1.UserService]
                    }), 
                    __metadata('design:paramtypes', [common_1.FormBuilder, user_service_1.UserService])
                ], FormUserComponent);
                return FormUserComponent;
            })();
            exports_1("FormUserComponent", FormUserComponent);
        }
    }
});
//# sourceMappingURL=form-user-component.js.map