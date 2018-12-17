import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentAddComponent } from './department-add/department-add.component';
import { DepartmentEditComponent } from './department-edit/department-edit.component';
import {DepartmentRoutingModule} from "./department-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
      DepartmentRoutingModule,
      TranslateModule,
      FormsModule
  ],
  declarations: [DepartmentListComponent, DepartmentAddComponent, DepartmentEditComponent]
})
export class DepartmentModule { }
