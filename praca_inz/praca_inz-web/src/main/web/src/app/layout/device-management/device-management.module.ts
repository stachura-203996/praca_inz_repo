import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeviceUserComponent } from './device-user/device-user.component';
import { DeviceRequestsComponent } from './device-requests/device-requests.component';
import { DeviceListComponent } from './device-list/device-list.component';
import {DeviceManagementRoutingModule} from "./device-management-routing.module";
import { DeviceAddComponent } from './device-add/device-add.component';

@NgModule({
  imports: [
    CommonModule,
      DeviceManagementRoutingModule
  ],
  declarations: [DeviceUserComponent, DeviceRequestsComponent, DeviceListComponent, DeviceAddComponent]
})
export class DeviceManagementModule { }
