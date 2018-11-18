import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile.component';
import { ProfileEditComponent } from './components/profile-edit/profile-edit.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ProfileComponent, ProfileEditComponent]
})
export class ProfileModule { }
