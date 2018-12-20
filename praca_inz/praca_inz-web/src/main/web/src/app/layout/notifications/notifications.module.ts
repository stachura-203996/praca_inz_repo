import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotificationUserComponent } from './notification-user/notification-user.component';
import {NotificationRoutingModule} from "./notification-routing.module";

@NgModule({
  imports: [
    CommonModule,
      NotificationRoutingModule
  ],
  declarations: [NotificationUserComponent]
})
export class NotificationsModule { }
