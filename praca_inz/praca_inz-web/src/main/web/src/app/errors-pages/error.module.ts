import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AccessDeniedComponent} from "./403/access-denied.component";
import {BadRequestComponent} from "./400/bad-request.component";
import {RequiresAuthenticationComponent} from "./401/requires-authentication.component";
import {NotFoundComponent} from "./404/not-found.component";
import {ServerErrorComponent} from "./500/server-error.component";
import {FormsModule} from "@angular/forms";
import {TranslateModule} from "@ngx-translate/core";
import {ErrorRoutingModule} from "./error-routing.module";

@NgModule({
  imports: [
    CommonModule,
      ErrorRoutingModule,
      TranslateModule,
      FormsModule
  ],
  declarations: [AccessDeniedComponent, BadRequestComponent, RequiresAuthenticationComponent,NotFoundComponent,ServerErrorComponent]
})
export class ErrorModule { }
