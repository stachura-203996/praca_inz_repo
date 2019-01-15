import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WarehouseUserComponent} from "./warehouse-user/warehouse-user.component";
import {WarehouseRequestListComponent} from "./warehouse-request-list/warehouse-request-list.component";
import {DeliveryListComponent} from "./delivery-list/delivery-list.component";
import {WarehouseDevicesComponent} from "./warehouse-devices/warehouse-devices.component";

const routes: Routes = [
    {path: '', component: WarehouseUserComponent },
    {path: 'requests', component: WarehouseRequestListComponent },
    {path:'devices', component: WarehouseDevicesComponent},
    {path:'delivery', component: DeliveryListComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class WarehouseManagementRoutingModule {}
