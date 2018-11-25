import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OfficeListComponent } from './office-list/office-list.component';
import { OfficeAddComponent } from './office-add/office-add.component';
import { OfficeEditComponent } from './office-edit/office-edit.component';
import {OfficeRoutingModule} from "./office-routing.module";

@NgModule({
  imports: [
    CommonModule,
      OfficeRoutingModule
  ],
  declarations: [OfficeListComponent, OfficeAddComponent, OfficeEditComponent]
})
export class OfficeModule { }
