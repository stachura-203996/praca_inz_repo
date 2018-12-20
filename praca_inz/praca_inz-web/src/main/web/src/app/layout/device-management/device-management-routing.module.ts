import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DeviceUserComponent} from "./device-user/device-user.component";
import {DeviceRequestListComponent} from "./device-request-list/device-request-list.component";
import {DeviceListComponent} from "./device-list/device-list.component";
import {DeviceAddComponent} from "./device-add/device-add.component";
import {DeviceEditComponent} from "./device-edit/device-edit.component";
import {TransferListComponent} from "./transfer-list/transfer-list.component";
import {TransferAddComponent} from "./transfer-add/transfer-add.component";
import {DeviceRequestUserComponent} from "./device-request-user/device-request-user.component";
import {DeviceRequestAddComponent} from "./device-request-add/device-request-add.component";
import {DeviceRequestEditComponent} from "./device-request-edit/device-request-edit.component";
import {DeviceRequestViewComponent} from "./device-request-view/device-request-view.component";
import {DeviceViewComponent} from "./device-view/device-view.component";
import {TransferViewComponent} from "./transfer-view/transfer-view.component";

const routes: Routes = [
    {path: '', component: DeviceListComponent },
    {path:'user', component: DeviceUserComponent},
    {path: 'add', component: DeviceListComponent },
    {path: 'edit', component: DeviceEditComponent},
    {path: 'view', component: DeviceViewComponent},
    {path:'transfer', component: TransferListComponent},
    {path:'transfer/user', component: TransferListComponent},
    {path:'transfer/view', component: TransferViewComponent},
    {path: 'request', component: DeviceRequestListComponent},
    {path: 'request/user', component: DeviceRequestUserComponent},
    {path: 'request/add', component: DeviceRequestAddComponent},
    {path: 'request/view', component: DeviceRequestViewComponent},
    {path: 'request/edit', component: DeviceRequestEditComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DeviceManagementRoutingModule {}
