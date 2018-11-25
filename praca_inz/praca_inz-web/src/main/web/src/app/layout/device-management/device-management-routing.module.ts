import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DeviceUserComponent} from "./device-user/device-user.component";
import {DeviceRequestsComponent} from "./device-requests/device-requests.component";
import {DeviceListComponent} from "./device-list/device-list.component";
import {DeviceAddComponent} from "./device-add/device-add.component";

const routes: Routes = [
    {path: '', component: DeviceListComponent },
    {path: 'requests', component: DeviceRequestsComponent},
    {path:'user', component: DeviceUserComponent},
    {path:'add', component: DeviceAddComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DeviceManagementRoutingModule {}
