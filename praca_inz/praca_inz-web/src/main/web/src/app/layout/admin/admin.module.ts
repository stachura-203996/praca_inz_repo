import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminRoutingModule} from "./admin-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {CompanyListComponent} from "./components/administration/company/company-list/company-list.component";
import {CompanyAddComponent} from "./components/administration/company/company-add/company-add.component";
import {CompanyEditComponent} from "./components/administration/company/company-edit/company-edit.component";
import {DepartmentListComponent} from "./components/structure-management/department/department-list/department-list.component";
import {DepartmentAddComponent} from "./components/structure-management/department/department-add/department-add.component";
import {DepartmentEditComponent} from "./components/structure-management/department/department-edit/department-edit.component";
import {OfficeListComponent} from "./components/structure-management/office/office-list/office-list.component";
import {OfficeAddComponent} from "./components/structure-management/office/office-add/office-add.component";
import {OfficeEditComponent} from "./components/structure-management/office/office-edit/office-edit.component";
import {UserEditComponent} from "./components/administration/user-management/components/user-edit/user-edit.component";
import {UserAddComponent} from "./components/administration/user-management/components/user-add/user-add.component";
import {UserListComponent} from "./components/administration/user-management/components/user-list/user-list.component";
import {DeviceListComponent} from "./components/structure-management/device/device-list/device-list.component";
import {DeviceAddComponent} from "./components/structure-management/device/device-add/device-add.component";
import {DeviceEditComponent} from "./components/structure-management/device/device-edit/device-edit.component";
import {TransferListComponent} from "./components/structure-management/device/transfer-list/transfer-list.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { WarehouseListComponent } from './components/structure-management/warehouse/warehouse-list/warehouse-list.component';
import {WarehouseAddComponent} from "./components/structure-management/warehouse/warehouse-add/warehouse-add.component";
import {WarehouseEditComponent} from "./components/structure-management/warehouse/warehouse-edit/warehouse-edit.component";
import { RequestListComponent } from './components/structure-management/request/request-list/request-list.component';
import { ReportListComponent } from './components/structure-management/report/report-list/report-list.component';
import {DeviceModelListComponent} from "./components/structure-management/device/device-model-list/device-model-list.component";
import {DeviceModelAddComponent} from "./components/structure-management/device/device-model-add/device-model-add.component";
import {DeviceModelEditComponent} from "./components/structure-management/device/device-model-edit/device-model-edit.component";
import { DeviceTypeListComponent } from './components/structure-management/device/device-type-list/device-type-list.component';
import { DeliveryListComponent } from './components/structure-management/warehouse/delivery-list/delivery-list.component';
import { SummaryComponent } from './components/structure-management/summary/summary.component';
import { UserPasswordEditComponent } from './components/administration/user-management/components/user-password-edit/user-password-edit.component';
import { DeviceTransferComponent } from './components/structure-management/device/device-transfer/device-transfer.component';
import {SystemMessageAddComponent} from "./components/administration/system_messages/system-message-add/system-message-add.component";
import {SystemMessageListComponent} from "./components/administration/system_messages/system-message-list/system-message-list.component";
import {ShareModule} from "../../shared/modules/share/share.module";
import { DeviceModelParametersChangeComponent } from './components/structure-management/device/device-model-parameters-change/device-model-parameters-change.component';

@NgModule({
    imports: [
        CommonModule,
        AdminRoutingModule,
        TranslateModule,
        FormsModule,
        ShareModule,
        NgbModule
    ],
    declarations: [
        CompanyListComponent,
        CompanyAddComponent,
        CompanyEditComponent,
        DepartmentListComponent,
        DepartmentAddComponent,
        DepartmentEditComponent,
        OfficeListComponent,
        OfficeAddComponent,
        OfficeEditComponent,
        UserEditComponent,
        UserAddComponent,
        UserListComponent,
        DeviceListComponent,
        DeviceAddComponent,
        DeviceEditComponent,
        TransferListComponent,
        DeviceModelListComponent,
        DeviceModelAddComponent,
        DeviceModelEditComponent,
        WarehouseListComponent,
        WarehouseAddComponent,
        WarehouseEditComponent,
        RequestListComponent,
        ReportListComponent,
        SystemMessageAddComponent,
        SystemMessageListComponent,
        DeviceTypeListComponent,
        DeliveryListComponent,
        SummaryComponent,
        UserPasswordEditComponent,
        DeviceTransferComponent,
        DeviceModelParametersChangeComponent,
    ]
})
export class AdminModule {
}
