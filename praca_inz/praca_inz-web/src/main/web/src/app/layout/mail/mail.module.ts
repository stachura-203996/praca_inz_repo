import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageAddComponent } from './components/message-add/message-add.component';
import { MailComponent } from './mail.component';
import {MailRoutingModule} from "./mail-routing.module";

@NgModule({
  imports: [
    CommonModule,
      MailRoutingModule
  ],
  declarations: [MessageAddComponent, MailComponent]
})
export class MailModule { }
