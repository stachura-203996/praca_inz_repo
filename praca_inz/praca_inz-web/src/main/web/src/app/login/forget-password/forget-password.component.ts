import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login.service";
import {PasswordReset} from "../../models/password-reset";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {

    constructor(public router: Router, private   loginService : LoginService) {
    }

    data:PasswordReset=new PasswordReset();

    ngOnInit() {
    }

    reset() {
        this.loginService.resetPassword(this.data);

        // this.router.navigateByUrl('/login')
    }
}
