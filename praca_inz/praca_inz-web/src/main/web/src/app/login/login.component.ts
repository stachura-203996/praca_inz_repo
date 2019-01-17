import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {routerTransition} from '../router.animations';
import {LoginService} from "./login.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    animations: [routerTransition()]
})
export class LoginComponent {

    constructor(public router: Router, private   loginService : LoginService) {
    }

    public loginData = {username: "", password: ""};

    onLoggedin() {
        this.loginService.obtainAccessToken(this.loginData);
        this.router.navigateByUrl('/')
    }
}
