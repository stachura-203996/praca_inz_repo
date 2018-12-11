import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProfileEditComponent} from './components/profile-edit/profile-edit.component';
import {ProfilePublicComponent} from './components/profile-public/profile-public.component';
import {ProfilePrivateComponent} from './components/profile-private/profile-private.component';
import {ProfileRoutingModule} from "./profile-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";

@NgModule({
    imports: [
        CommonModule,
        ProfileRoutingModule,
        TranslateModule,
        FormsModule
    ],
    declarations: [ProfileEditComponent, ProfilePublicComponent, ProfilePrivateComponent],

})
export class ProfileModule {
}
