import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProfileRoutingModule} from "./profile-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ProfileEditComponent} from "./profile-edit/profile-edit.component";
import {ProfileComponent} from "./profile/profile.component";

@NgModule({
    imports: [
        CommonModule,
        ProfileRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [ProfileEditComponent, ProfileComponent],

})
export class ProfileModule {
}
