import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotificationUserComponent } from './notification-user/notification-user.component';
import {NotificationRoutingModule} from "./notification-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
      NotificationRoutingModule,
      TranslateModule,
      FormsModule
  ],
  declarations: [NotificationUserComponent]
})
export class NotificationsModule { }
