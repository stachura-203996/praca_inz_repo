import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserAddComponent} from "./components/user-add/user-add.component";
import {UserListComponent} from "./components/user-list/user-list.component";
import {UserEditComponent} from "./components/user-edit/user-edit.component";

const routes: Routes = [
    {path: '', component: UserListComponent },
    {path: 'edit', component: UserEditComponent},
    {path:'add', component: UserAddComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserManagementRoutingModule {}
