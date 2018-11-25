import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationViewComponent } from './notification-view/notification-view.component';
import { NotificationUserComponent } from './notification-user/notification-user.component';
import {NotificationRoutingModule} from "./notification-routing.module";

@NgModule({
  imports: [
    CommonModule,
      NotificationRoutingModule
  ],
  declarations: [NotificationViewComponent, NotificationUserComponent]
})
export class NotificationsModule { }
