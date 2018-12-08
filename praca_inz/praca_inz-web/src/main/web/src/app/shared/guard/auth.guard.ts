import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { Router } from '@angular/router';
import {LoginService} from "../../login/login.service";

@Injectable()
export class AuthGuard {
    constructor(private router: Router/*, private service:LoginService*/) {}

    canActivate() {
        // return this.service.checkCredentials();

        if (localStorage.getItem('isLoggedin')) {

            return true;
        }

        this.router.navigate(['/login']);
        return false;
    }
}
