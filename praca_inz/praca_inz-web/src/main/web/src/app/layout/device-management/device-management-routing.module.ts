import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DeviceUserComponent} from "./device-user/device-user.component";
import {DeviceViewComponent} from "./device-view/device-view.component";
import {TransferUserComponent} from "./transfer-user/transfer-user.component";
import {DeviceRequestAddComponent} from "./device-requests/device-request-add/device-request-add.component";
import {DeviceRequestViewComponent} from "./device-requests/device-request-view/device-request-view.component";
import {DeviceModelViewComponent} from "./device-model-view/device-model-view.component";
import {TransferRequestAddComponent} from "./transfer-requests/transfer-request-add/transfer-request-add.component";
import {TransferRequestViewComponent} from "./transfer-requests/transfer-request-view/transfer-request-view.component";
import {DeviceRequestViewWarehouseComponent} from "./device-requests/device-request-view-warehouse/device-request-view-warehouse.component";


const routes: Routes = [
    {path:'', component: DeviceUserComponent},
    {path: 'view/:id', component: DeviceViewComponent},
    {path:'transfer', component: TransferUserComponent},
    {path:'transfer/request/add/:id', component: TransferRequestAddComponent},
    {path:'transfer/request/view/:id', component: TransferRequestViewComponent},
    {path: 'request/add', component: DeviceRequestAddComponent},
    {path: 'request/view/:id', component: DeviceRequestViewComponent},
    {path: 'request/warehouse/view/:id', component: DeviceRequestViewWarehouseComponent},
    {path: 'model/view/:id', component: DeviceModelViewComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DeviceManagementRoutingModule {}
