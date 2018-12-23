import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DeviceUserComponent} from './device-user/device-user.component';
import {DeviceManagementRoutingModule} from "./device-management-routing.module";
import {TransferUserComponent} from './transfer-user/transfer-user.component';
import {DeviceRequestAddComponent} from './device-request-add/device-request-add.component';
import {DeviceRequestEditComponent} from './device-request-edit/device-request-edit.component';
import {DeviceRequestUserComponent} from './device-request-user/device-request-user.component';
import {DeviceViewComponent} from './device-view/device-view.component';
import {DeviceRequestViewComponent} from './device-request-view/device-request-view.component';
import {TransferViewComponent} from './transfer-view/transfer-view.component';
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import { DeviceTypeViewComponent } from './device-type-view/device-type-view.component';
import { TransferRequestAddComponent } from './transfer-request-add/transfer-request-add.component';

@NgModule({
    imports: [
        CommonModule,
        DeviceManagementRoutingModule,
        TranslateModule,
        FormsModule
    ],
    declarations: [
        DeviceUserComponent,
        TransferUserComponent,
        DeviceRequestAddComponent,
        DeviceRequestEditComponent,
        DeviceRequestUserComponent,
        DeviceViewComponent,
        DeviceRequestViewComponent,
        TransferViewComponent,
        DeviceTypeViewComponent,
        TransferRequestAddComponent
    ]
})
export class DeviceManagementModule {
}
