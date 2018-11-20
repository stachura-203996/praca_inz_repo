import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserAddComponent } from './components/user-add/user-add.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [UserAddComponent, UserListComponent, UserEditComponent]
})
export class UserManagementModule { }
