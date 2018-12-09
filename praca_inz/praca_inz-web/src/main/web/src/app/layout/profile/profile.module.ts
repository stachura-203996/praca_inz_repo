import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProfileEditComponent} from './components/profile-edit/profile-edit.component';
import {ProfilePublicComponent} from './components/profile-public/profile-public.component';
import {ProfilePrivateComponent} from './components/profile-private/profile-private.component';
import {ProfileRoutingModule} from "./profile-routing.module";
import {ProfileService} from "./profile.service";

@NgModule({
    imports: [
        CommonModule,
        ProfileRoutingModule
    ],
    declarations: [ProfileEditComponent, ProfilePublicComponent, ProfilePrivateComponent],

})
export class ProfileModule {
}
