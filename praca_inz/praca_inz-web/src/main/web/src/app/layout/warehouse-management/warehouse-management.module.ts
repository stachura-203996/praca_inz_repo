import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WarehouseUserComponent} from './warehouse-user/warehouse-user.component';
import {WarehouseManagementRoutingModule} from "./warehouse-management-routing.module";
import {WarehouseViewComponent} from './warehouse-view/warehouse-view.component';
import {DeliveryRequestViewComponent} from './delivery-request-view/delivery-request-view.component';
import {DeliveryRequestAddComponent} from './delivery-request-add/delivery-request-add.component';
import { DeliveryRequestEditComponent } from './delivery-request-edit/delivery-request-edit.component';
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import { ShipmentRequestAddComponent } from './shipment-request-add/shipment-request-add.component';
import { DeviceAddComponent } from './device-add/device-add.component';
import { DeviceEditComponent } from './device-edit/device-edit.component';
import { ShipmentRequestEditComponent } from './shipment-request-edit/shipment-request-edit.component';
import { WarehouseRequestListComponent } from './warehouse-request-list/warehouse-request-list.component';
import { ShipmentRequestViewComponent } from './shipment-request-view/shipment-request-view.component';
import { DeliveryListComponent } from './delivery-list/delivery-list.component';
import { ShipmentListComponent } from './shipment-list/shipment-list.component';
import { WarehouseDevicesComponent } from './warehouse-devices/warehouse-devices.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { DeviceModelAddComponent } from './device-model-add/device-model-add.component';
import { DeviceModelEditComponent } from './device-model-edit/device-model-edit.component';
import { DeviceModelListComponent } from './device-model-list/device-model-list.component';
import { SummaryComponent } from './summary/summary.component';


@NgModule({
    imports: [
        CommonModule,
        WarehouseManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [
        WarehouseUserComponent,
        WarehouseViewComponent,
        DeliveryRequestViewComponent,
        DeliveryRequestAddComponent,
        DeliveryRequestEditComponent,
        ShipmentRequestAddComponent,
        DeviceAddComponent,
        DeviceEditComponent,
        ShipmentRequestEditComponent,
        WarehouseRequestListComponent,
        ShipmentRequestViewComponent,
        DeliveryListComponent,
        ShipmentListComponent,
        WarehouseDevicesComponent,
        DeviceModelAddComponent,
        DeviceModelEditComponent,
        DeviceModelListComponent,
        SummaryComponent
    ]
})
export class WarehouseManagementModule {
}
