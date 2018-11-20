import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageAddComponent } from './components/message-add/message-add.component';
import { MailComponent } from './mail.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [MessageAddComponent, MailComponent]
})
export class MailModule { }
