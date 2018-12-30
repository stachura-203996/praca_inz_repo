import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WarehouseListComponent} from './warehouse-list/warehouse-list.component';
import {WarehouseManagementRoutingModule} from "./warehouse-management-routing.module";
import {DeliveryRequestListComponent} from './delivery-request-list/delivery-request-list.component';
import {WarehouseViewComponent} from './warehouse-view/warehouse-view.component';
import {DeliveryRequestViewComponent} from './delivery-request-view/delivery-request-view.component';
import {DeliveryRequestAddComponent} from './delivery-request-add/delivery-request-add.component';
import { DeliveryRequestEditComponent } from './delivery-request-edit/delivery-request-edit.component';
import {WarehouseEditComponent} from "./warehouse-edit/warehouse-edit.component";
import {WarehouseAddComponent} from "./warehouse-add/warehouse-add.component";
import { WarehousesDeliveryComponent } from './warehouses-delivery/warehouses-delivery.component';


@NgModule({
    imports: [
        CommonModule,
        WarehouseManagementRoutingModule
    ],
    declarations: [
        WarehouseListComponent,
        DeliveryRequestListComponent,
        WarehouseViewComponent,
        WarehouseEditComponent,
        WarehouseAddComponent,
        DeliveryRequestViewComponent,
        DeliveryRequestAddComponent,
        DeliveryRequestEditComponent,
        WarehousesDeliveryComponent
    ]
})
export class WarehouseManagementModule {
}
