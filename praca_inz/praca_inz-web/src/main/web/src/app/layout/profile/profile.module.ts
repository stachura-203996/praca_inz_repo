import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile.component';
import { ProfileEditComponent } from './components/profile-edit/profile-edit.component';
import { ProfilePublicComponent } from './components/profile-public/profile-public.component';
import { ProfilePrivateComponent } from './components/profile-private/profile-private.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ProfileComponent, ProfileEditComponent, ProfilePublicComponent, ProfilePrivateComponent]
})
export class ProfileModule { }
