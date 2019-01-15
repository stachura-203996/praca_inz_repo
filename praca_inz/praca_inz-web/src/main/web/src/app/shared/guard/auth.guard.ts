import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { Router } from '@angular/router';
import {LoginService} from "../../login/login.service";

@Injectable()
export class AuthGuard {
    constructor(private router: Router, private service:LoginService) {}

    canActivate() {

        if (this.service.checkCredentials()) {
            return true;
        }

        this.router.navigate(['/login']);
        return false;
    }
}
