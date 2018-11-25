import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyListComponent } from './company-list/company-list.component';
import { CompanyAddComponent } from './company-add/company-add.component';
import { CompanyEditComponent } from './company-edit/company-edit.component';
import {CompanyRoutingModule} from "./company-routing.module";

@NgModule({
  imports: [
    CommonModule,
      CompanyRoutingModule
  ],
  declarations: [CompanyListComponent, CompanyAddComponent, CompanyEditComponent]
})
export class CompanyModule { }
