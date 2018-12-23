import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProfileEditComponent} from './components/profile-edit/profile-edit.component';
import {ProfileComponent} from './components/profile/profile.component';
import {ProfileRoutingModule} from "./profile-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
    imports: [
        CommonModule,
        ProfileRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot(),
    ],
    declarations: [ProfileEditComponent, ProfileComponent],

})
export class ProfileModule {
}
