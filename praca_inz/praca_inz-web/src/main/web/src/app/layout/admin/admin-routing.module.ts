import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CompanyListComponent} from "./components/administration/company/company-list/company-list.component";
import {CompanyAddComponent} from "./components/administration/company/company-add/company-add.component";
import {CompanyEditComponent} from "./components/administration/company/company-edit/company-edit.component";
import {OfficeListComponent} from "./components/structure-management/office/office-list/office-list.component";
import {OfficeAddComponent} from "./components/structure-management/office/office-add/office-add.component";
import {OfficeEditComponent} from "./components/structure-management/office/office-edit/office-edit.component";
import {DepartmentListComponent} from "./components/structure-management/department/department-list/department-list.component";
import {DepartmentAddComponent} from "./components/structure-management/department/department-add/department-add.component";
import {DepartmentEditComponent} from "./components/structure-management/department/department-edit/department-edit.component";
import {UserListComponent} from "./components/administration/user-management/components/user-list/user-list.component";
import {UserAddComponent} from "./components/administration/user-management/components/user-add/user-add.component";
import {UserEditComponent} from "./components/administration/user-management/components/user-edit/user-edit.component";
import {DeviceListComponent} from "./components/structure-management/device/device-list/device-list.component";
import {DeviceAddComponent} from "./components/structure-management/device/device-add/device-add.component";
import {DeviceEditComponent} from "./components/structure-management/device/device-edit/device-edit.component";
import {TransferListComponent} from "./components/structure-management/device/transfer-list/transfer-list.component";
import {WarehouseListComponent} from "./components/structure-management/warehouse/warehouse-list/warehouse-list.component";
import {WarehouseAddComponent} from "./components/structure-management/warehouse/warehouse-add/warehouse-add.component";
import {WarehouseEditComponent} from "./components/structure-management/warehouse/warehouse-edit/warehouse-edit.component";
import {RequestListComponent} from "./components/structure-management/request/request-list/request-list.component";
import {ReportListComponent} from "./components/structure-management/report/report-list/report-list.component";
import {DeviceModelAddComponent} from "./components/structure-management/device/device-model-add/device-model-add.component";
import {DeviceModelEditComponent} from "./components/structure-management/device/device-model-edit/device-model-edit.component";
import {DeviceModelListComponent} from "./components/structure-management/device/device-model-list/device-model-list.component";
import {DeliveryListComponent} from "./components/structure-management/warehouse/delivery-list/delivery-list.component";
import {UserPasswordEditComponent} from "./components/administration/user-management/components/user-password-edit/user-password-edit.component";
import {DeviceTransferComponent} from "./components/structure-management/device/device-transfer/device-transfer.component";
import {DeviceTypeListComponent} from "./components/structure-management/device/device-type-list/device-type-list.component";
import {SystemMessageListComponent} from "./components/administration/system_messages/system-message-list/system-message-list.component";
import {SystemMessageAddComponent} from "./components/administration/system_messages/system-message-add/system-message-add.component";
import {GeneralRouteGuard} from "../../shared/guard/general-route-guard.service";
import {FullRouteGuard} from "../../shared/guard/full-route-guard.service";



const routes: Routes = [
    {path: 'companies', component: CompanyListComponent,/*canActivate: [FullRouteGuard]*/},
    {path: 'companies/add', component:CompanyAddComponent/*,canActivate: [FullRouteGuard]*/},
    {path: 'companies/edit/:id', component:CompanyEditComponent/*,canActivate: [FullRouteGuard]*/},
    {path: 'offices', component: OfficeListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'offices/add', component:OfficeAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'offices/edit/:id', component:OfficeEditComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'departments', component: DepartmentListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'departments/add', component:DepartmentAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'departments/edit/:id', component:DepartmentEditComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'users', component: UserListComponent/*,canActivate: [FullRouteGuard]*/},
    {path: 'users/add', component:UserAddComponent/*,canActivate: [FullRouteGuard]*/},
    {path: 'users/edit/:id', component:UserEditComponent/*,canActivate: [FullRouteGuard]*/},
    {path: 'users/edit/password/:id', component:UserPasswordEditComponent/*,canActivate: [FullRouteGuard]*/},
    {path: 'devices', component: DeviceListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/add', component:DeviceAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/edit/:id', component:DeviceEditComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/transfer/:id', component: DeviceTransferComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/model', component: DeviceModelListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/model/add', component: DeviceModelAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/model/edit/:id', component: DeviceModelEditComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'devices/type', component: DeviceTypeListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'warehouses', component: WarehouseListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'warehouses/add', component: WarehouseAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'warehouses/edit/:id', component: WarehouseEditComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'deliveries', component: DeliveryListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'requests', component: RequestListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'reports', component: ReportListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'transfers', component: TransferListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'system/messages', component: SystemMessageListComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'system/messages/add', component: SystemMessageAddComponent/*,canActivate: [GeneralRouteGuard]*/}


];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule {
}
