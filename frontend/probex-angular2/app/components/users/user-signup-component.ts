import {Location} from '@angular/common';
import {Component} from '@angular/core';
import {User} from '../../models/user';
import {UserService} from '../../services/user-service';
import {OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MdSnackBar} from "@angular/material";

@Component({
    selector: 'user-signup',
    templateUrl: 'app/views/users/signup.html',
    providers: [UserService]
})
export class UserSignupComponent implements OnInit {

    private user: User;

    error: string;

    constructor(private _location: Location,
                private router: Router,
                private userService:
                    UserService,
                public snackBar: MdSnackBar) {
    }

    goBack(){
        this._location.back();
    }

    ngOnInit() {
        this.user = new User();
    }

    signUp() {
        this.userService.insert(this.user).subscribe(
            data => {
                this.snackBar.open("Usuario cadastrado com suceso", "OK");
                this.router.navigate(['/user-list']);
            },
            error => this.snackBar.open("Erro: " + error._body, "OK")
        );
    }
}