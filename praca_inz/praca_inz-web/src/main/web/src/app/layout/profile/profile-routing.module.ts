import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfileComponent} from "./profile/profile.component";
import {ProfileEditComponent} from "./profile-edit/profile-edit.component";
import {PasswordEditComponent} from "./password-edit/password-edit.component";

const routes: Routes = [
    {path: '', component: ProfileComponent },
    {path: 'edit', component: ProfileEditComponent},
    {path: 'password', component: PasswordEditComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProfileRoutingModule {}
