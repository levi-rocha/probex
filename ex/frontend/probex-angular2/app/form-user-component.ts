import { Component, OnInit } from 'angular2/core';
import { Permission } from "./model/permission";
import { User } from "./model/user";
import {ControlGroup, FormBuilder, Validators, AbstractControl} from 'angular2/common';
import {EmailValidator} from './validators/email-validator';
import {UserService} from './service/user-service';

@Component({
	selector: 'form-user',
	templateUrl: 'app/view/form-user.html',
    providers: [UserService]
})
export class FormUserComponent implements OnInit{

    success: boolean = false;

    editId: number;
    users: User[];

    permissions: Permission[];
    user: User;

    userForm: ControlGroup;

    constructor(private fb: FormBuilder, private userService: UserService) {
        
    }

    ngOnInit() {
        this.user = new User();
        this.permissions = [
            new Permission('standard', 'Standard'),
            new Permission('enterprise', 'Enterprise'),
            new Permission('admin', 'Administrator')
        ];
        this.buildForm(this.fb);

        this.editId = -1;
        this.users = this.userService.listAll();
    }

    buildForm(fb: FormBuilder): void {
        this.userForm = fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
            email: ['', Validators.compose([Validators.required, EmailValidator.validate])],
            permission: ['', Validators.required]
        });
    }

    insert() {
        this.userService.insert(this.user);
        this.user = new User();
    }

    edit(id: number) {
        this.editId = id;
        this.user = new User(this.users[id].username, this.users[id].password, this.users[id].email, this.users[id].permission);
    }

    update() {
        this.userService.update(this.editId, this.user);
        this.user = new User();
        this.editId = -1;
    }

    delete(id: number) {
        this.userService.delete(id);
        this.editId = -1;
    }

}