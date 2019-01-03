import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DeviceUserComponent} from "./device-user/device-user.component";
import {DeviceRequestUserComponent} from "./device-request-user/device-request-user.component";
import {DeviceRequestAddComponent} from "./device-request-add/device-request-add.component";
import {DeviceRequestEditComponent} from "./device-request-edit/device-request-edit.component";
import {DeviceRequestViewComponent} from "./device-request-view/device-request-view.component";
import {DeviceViewComponent} from "./device-view/device-view.component";
import {TransferViewComponent} from "./transfer-view/transfer-view.component";
import {TransferUserComponent} from "./transfer-user/transfer-user.component";
import {DeviceTypeViewComponent} from "./device-type-view/device-type-view.component";

const routes: Routes = [
    {path:'', component: DeviceUserComponent},
    {path: 'view/:id', component: DeviceViewComponent},
    {path:'transfer', component: TransferUserComponent},
    {path:'transfer/view/:id', component: TransferViewComponent},
    {path: 'request', component: DeviceRequestUserComponent},
    {path: 'request/add', component: DeviceRequestAddComponent},
    {path: 'request/view/:id', component: DeviceRequestViewComponent},
    {path: 'request/edit', component: DeviceRequestEditComponent},
    {path: 'type/view/:id', component: DeviceTypeViewComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DeviceManagementRoutingModule {}
