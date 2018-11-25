import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WarehouseListComponent } from './warehouse-list/warehouse-list.component';
import {WarehouseManagementRoutingModule} from "./warehouse-management-routing.module";

@NgModule({
  imports: [
    CommonModule,
      WarehouseManagementRoutingModule
  ],
  declarations: [WarehouseListComponent]
})
export class WarehouseManagementModule { }
