import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeviceUserComponent } from './device-user/device-user.component';
import { DeviceRequestListComponent } from './device-request-list/device-request-list.component';
import { DeviceListComponent } from './device-list/device-list.component';
import {DeviceManagementRoutingModule} from "./device-management-routing.module";
import { DeviceAddComponent } from './device-add/device-add.component';
import { TransferUserComponent } from './transfer-user/transfer-user.component';
import { DeviceRequestAddComponent } from './device-request-add/device-request-add.component';
import { DeviceEditComponent } from './device-edit/device-edit.component';
import { TransferListComponent } from './transfer-list/transfer-list.component';
import { TransferAddComponent } from './transfer-add/transfer-add.component';
import { DeviceRequestEditComponent } from './device-request-edit/device-request-edit.component';
import { DeviceRequestUserComponent } from './device-request-user/device-request-user.component';
import { DeviceViewComponent } from './device-view/device-view.component';
import { DeviceRequestViewComponent } from './device-request-view/device-request-view.component';
import { TransferViewComponent } from './transfer-view/transfer-view.component';

@NgModule({
  imports: [
    CommonModule,
      DeviceManagementRoutingModule
  ],
  declarations: [DeviceUserComponent, DeviceRequestListComponent, DeviceListComponent, DeviceAddComponent, TransferUserComponent, DeviceRequestAddComponent, DeviceEditComponent, TransferListComponent, TransferAddComponent, DeviceRequestEditComponent, DeviceRequestUserComponent, DeviceViewComponent, DeviceRequestViewComponent, TransferViewComponent]
})
export class DeviceManagementModule { }
