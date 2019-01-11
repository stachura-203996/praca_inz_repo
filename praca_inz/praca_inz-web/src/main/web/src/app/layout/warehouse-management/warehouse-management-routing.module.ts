import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WarehouseUserComponent} from "./warehouse-user/warehouse-user.component";
import {WarehouseViewComponent} from "./warehouse-view/warehouse-view.component";
import {WarehouseRequestListComponent} from "./warehouse-request-list/warehouse-request-list.component";
import {DeliveryListComponent} from "./delivery-list/delivery-list.component";
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
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class WarehouseManagementRoutingModule {}
