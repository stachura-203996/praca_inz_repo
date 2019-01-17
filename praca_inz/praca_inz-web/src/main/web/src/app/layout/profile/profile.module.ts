import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProfileRoutingModule} from "./profile-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ProfileEditComponent} from "./profile-edit/profile-edit.component";
import {ProfileComponent} from "./profile/profile.component";
import { PasswordEditComponent } from './password-edit/password-edit.component';
import {ShareModule} from "../../shared/modules/share/share.module";

@NgModule({
    imports: [
        CommonModule,
        ProfileRoutingModule,
        TranslateModule,
        FormsModule,
        ShareModule,
        NgbModule.forRoot()
    ],
    declarations: [ProfileEditComponent, ProfileComponent, PasswordEditComponent],

})
export class ProfileModule {
}
