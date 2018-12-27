import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WarehouseUserComponent} from './warehouse-user/warehouse-user.component';
import {WarehouseManagementRoutingModule} from "./warehouse-management-routing.module";
import {DeliveryRequestListComponent} from './delivery-request-list/delivery-request-list.component';
import {WarehouseViewComponent} from './warehouse-view/warehouse-view.component';
import {DeliveryRequestViewComponent} from './delivery-request-view/delivery-request-view.component';
import {DeliveryRequestAddComponent} from './delivery-request-add/delivery-request-add.component';
import { DeliveryRequestEditComponent } from './delivery-request-edit/delivery-request-edit.component';
import { WarehousesDeliveryComponent } from './warehouses-delivery/warehouses-delivery.component';
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";


@NgModule({
    imports: [
        CommonModule,
        WarehouseManagementRoutingModule,
        TranslateModule,
        FormsModule,
    ],
    declarations: [
        WarehouseUserComponent,
        DeliveryRequestListComponent,
        WarehouseViewComponent,
        DeliveryRequestViewComponent,
        DeliveryRequestAddComponent,
        DeliveryRequestEditComponent,
        WarehousesDeliveryComponent
    ]
})
export class WarehouseManagementModule {
}
