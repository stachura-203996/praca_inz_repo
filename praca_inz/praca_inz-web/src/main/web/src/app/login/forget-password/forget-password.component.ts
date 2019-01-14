import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login.service";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {

    constructor(public router: Router, private   loginService : LoginService) {
    }

    loginData:string;

    ngOnInit() {
    }

    reset() {
        this.loginService.resetPassword(this.loginData)
        this.router.navigateByUrl('/login')
    }
}
