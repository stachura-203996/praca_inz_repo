import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DeviceUserComponent} from './device-user/device-user.component';
import {DeviceManagementRoutingModule} from "./device-management-routing.module";
import {TransferUserComponent} from './transfer-user/transfer-user.component';

import {DeviceViewComponent} from './device-view/device-view.component';
import {TransferViewComponent} from './transfer-view/transfer-view.component';
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";


import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {DeviceRequestAddComponent} from "./device-requests/device-request-add/device-request-add.component";
import {DeviceRequestEditComponent} from "./device-requests/device-request-edit/device-request-edit.component";
import {DeviceRequestViewComponent} from "./device-requests/device-request-view/device-request-view.component";
import {TransferRequestAddComponent} from "./transfer-requests/transfer-request-add/transfer-request-add.component";
import {TransferRequestViewComponent} from "./transfer-requests/transfer-request-view/transfer-request-view.component";
import {TransferRequestEditComponent} from "./transfer-requests/transfer-request-edit/transfer-request-edit.component";
import {DeviceModelViewComponent} from "./device-model-view/device-model-view.component";


@NgModule({
    imports: [
        CommonModule,
        DeviceManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [
        DeviceUserComponent,
        TransferUserComponent,
        DeviceRequestAddComponent,
        DeviceRequestEditComponent,
        DeviceViewComponent,
        DeviceRequestViewComponent,
        TransferViewComponent,
        DeviceModelViewComponent,
        TransferRequestAddComponent,
        TransferRequestViewComponent,
        TransferRequestEditComponent
    ]
})
export class DeviceManagementModule {
}
