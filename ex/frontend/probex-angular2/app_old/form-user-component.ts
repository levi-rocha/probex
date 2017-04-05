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

    error: string;
    message: string = "";

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
        this.listAll();
    }

    buildForm(fb: FormBuilder): void {
        this.userForm = fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required],
            email: ['', Validators.compose([Validators.required, EmailValidator.validate])],
            permission: ['', Validators.required]
        });
    }

    listAll() {
        this.userService.listAll().subscribe(
            data => this.users = data,
            error => this.error = "Could not list users"
        );
    }

    insert() {
        this.userService.insert(this.user).subscribe(
            data => this.message = data,
            error => this.error = "Could not save user",
            () => this.listAll()
        );

        this.user = new User();
    }

    edit(user: User) {
        this.editId = user.id;
        this.user = user;
    }

    update() {
        this.userService.update(this.user).subscribe(
            data => this.message = data,
            error => this.error = "Could not update user",
            () => this.listAll()
        );
        this.user = new User();
        this.editId = -1;
    }

    delete(user: User) {
        this.userService.delete(user.id).subscribe(
            data => this.message = data,
            error => this.error = "Could not delete user",
            () => this.listAll()
        );
    }

}