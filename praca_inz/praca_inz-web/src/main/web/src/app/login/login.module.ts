import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import {LoginService} from "./login.service";
import {CookieService} from "ngx-cookie-service";
import {FormsModule} from "@angular/forms";

@NgModule({
    imports: [CommonModule, LoginRoutingModule,FormsModule],
    providers: [LoginService,CookieService],
    declarations: [LoginComponent]
})
export class LoginModule {}
