import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';

import {FormsModule} from "@angular/forms";
import {TranslateModule} from "@ngx-translate/core";
import {NgbDropdownModule} from "@ng-bootstrap/ng-bootstrap";
import {ForgetPasswordComponent} from "./forget-password/forget-password.component";

@NgModule({
    imports: [
        CommonModule,
        LoginRoutingModule,
        FormsModule,
        TranslateModule,
        NgbDropdownModule
    ],
    declarations: [LoginComponent,ForgetPasswordComponent]
})
export class LoginModule {}
