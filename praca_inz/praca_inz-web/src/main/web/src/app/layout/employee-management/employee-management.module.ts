import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubordinatesListComponent } from './subordinates-list/subordinates-list.component';
import { SubordinatesRequestsComponent } from './subordinates-requests/subordinates-requests.component';
import {EmployeeManagementRoutingModule} from "./employee-management-routing.module";

@NgModule({
  imports: [
    CommonModule,
      EmployeeManagementRoutingModule
  ],
  declarations: [SubordinatesListComponent, SubordinatesRequestsComponent]
})
export class EmployeeManagementModule { }
