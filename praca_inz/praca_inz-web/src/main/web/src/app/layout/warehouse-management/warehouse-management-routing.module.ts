import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WarehouseUserComponent} from "./warehouse-user/warehouse-user.component";
import {WarehouseViewComponent} from "./warehouse-view/warehouse-view.component";
import {DeliveryRequestAddComponent} from "./delivery-request-add/delivery-request-add.component";
import {DeliveryRequestViewComponent} from "./delivery-request-view/delivery-request-view.component";
import {WarehouseRequestListComponent} from "./warehouse-request-list/warehouse-request-list.component";
import {ShipmentRequestAddComponent} from "./shipment-request-add/shipment-request-add.component";
import {ShipmentRequestViewComponent} from "./shipment-request-view/shipment-request-view.component";
import {DeliveryListComponent} from "./delivery-list/delivery-list.component";
import {ShipmentListComponent} from "./shipment-list/shipment-list.component";
import {DeviceAddComponent} from "./device-add/device-add.component";
import {DeviceEditComponent} from "./device-edit/device-edit.component";
import {WarehouseDevicesComponent} from "./warehouse-devices/warehouse-devices.component";


const routes: Routes = [
    {path: '', component: WarehouseUserComponent },
    {path: 'view/:id', component: WarehouseViewComponent },
    {path: 'requests', component: WarehouseRequestListComponent },
    {path:'devices', component: WarehouseDevicesComponent},
    {path:'device/add', component: DeviceAddComponent},
    {path:'device/edit/:id', component: DeviceEditComponent},
    {path:'delivery', component: DeliveryListComponent},
    {path:'shipment', component: ShipmentListComponent},
    {path:'delivery/request/add', component: DeliveryRequestAddComponent},
    {path:'delivery/request/view/:id', component: DeliveryRequestViewComponent},
    {path:'shipment/request/add', component: ShipmentRequestAddComponent},
    {path:'shipment/request/view/:id', component: ShipmentRequestViewComponent},



];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class WarehouseManagementRoutingModule {}
