import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {WarehouseUserComponent} from './warehouse-user/warehouse-user.component';
import {WarehouseManagementRoutingModule} from "./warehouse-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import { WarehouseRequestListComponent } from './warehouse-request-list/warehouse-request-list.component';
import { WarehouseDevicesComponent } from './warehouse-devices/warehouse-devices.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ExternalTransferListComponent} from "./external-transfer-list/external-transfer-list.component";


@NgModule({
    imports: [
        CommonModule,
        WarehouseManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [
        WarehouseUserComponent,
        WarehouseRequestListComponent,
        ExternalTransferListComponent,
        WarehouseDevicesComponent,
    ]
})
export class WarehouseManagementModule {
}
