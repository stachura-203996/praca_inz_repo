import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfileEditComponent} from "./components/profile-edit/profile-edit.component";
import {ProfilePrivateComponent} from "./components/profile-private/profile-private.component";
import {ProfilePublicComponent} from "./components/profile-public/profile-public.component";

const routes: Routes = [
    {path: '', component: ProfilePrivateComponent },
    {path: 'edit', component: ProfileEditComponent},
    {path:'user', component: ProfilePublicComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProfileRoutingModule {}
