import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserAddComponent} from './components/user-add/user-add.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserEditComponent} from './components/user-edit/user-edit.component';
import {UserManagementRoutingModule} from "./user-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";

@NgModule({
    imports: [
        CommonModule,
        UserManagementRoutingModule,
        TranslateModule,
        FormsModule

    ],
    declarations: [UserAddComponent, UserListComponent, UserEditComponent]
})
export class UserManagementModule {
}
