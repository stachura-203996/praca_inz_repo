import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WarehouseUserComponent} from "./warehouse-user/warehouse-user.component";
import {WarehouseRequestListComponent} from "./warehouse-request-list/warehouse-request-list.component";
import {WarehouseDevicesComponent} from "./warehouse-devices/warehouse-devices.component";
import {ExternalTransferListComponent} from "./external-transfer-list/external-transfer-list.component";

const routes: Routes = [
    {path: '', component: WarehouseUserComponent},
    {path: 'requests', component: WarehouseRequestListComponent},
    {path:'devices', component: WarehouseDevicesComponent},
    {path:'transfer/external', component: ExternalTransferListComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class WarehouseManagementRoutingModule {}
