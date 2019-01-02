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
import {UserViewComponent} from "./components/administration/user-management/components/user-view/user-view.component";
import {DeviceListComponent} from "./components/structure-management/device/device-list/device-list.component";
import {DeviceAddComponent} from "./components/structure-management/device/device-add/device-add.component";
import {DeviceEditComponent} from "./components/structure-management/device/device-edit/device-edit.component";
import {DeviceRequestListComponent} from "./components/structure-management/device/device-request-list/device-request-list.component";
import {TransferListComponent} from "./components/structure-management/device/transfer-list/transfer-list.component";
import {DeviceParameterAddComponent} from "./components/structure-management/device/device-parameter-add/device-parameter-add.component";
import {DeviceParameterEditComponent} from "./components/structure-management/device/device-parameter-edit/device-parameter-edit.component";
import {WarehouseListComponent} from "./components/structure-management/warehouse/warehouse-list/warehouse-list.component";
import {WarehouseAddComponent} from "./components/structure-management/warehouse/warehouse-add/warehouse-add.component";
import {WarehouseEditComponent} from "./components/structure-management/warehouse/warehouse-edit/warehouse-edit.component";
import {RequestListComponent} from "./components/structure-management/request/request-list/request-list.component";
import {ReportListComponent} from "./components/structure-management/report/report-list/report-list.component";
import {DeviceModelAddComponent} from "./components/structure-management/device/device-model-add/device-model-add.component";
import {DeviceModelEditComponent} from "./components/structure-management/device/device-model-edit/device-model-edit.component";
import {DeviceModelListComponent} from "./components/structure-management/device/device-model-list/device-model-list.component";



const routes: Routes = [
    {path: 'companies', component: CompanyListComponent},
    {path: 'companies/add', component:CompanyAddComponent},
    {path: 'companies/edit/:id', component:CompanyEditComponent},
    {path: 'offices', component: OfficeListComponent},
    {path: 'offices/add', component:OfficeAddComponent},
    {path: 'offices/edit/:id', component:OfficeEditComponent},
    {path: 'departments', component: DepartmentListComponent},
    {path: 'departments/add', component:DepartmentAddComponent},
    {path: 'departments/edit/:id', component:DepartmentEditComponent},
    {path: 'users', component: UserListComponent},
    {path: 'users/add', component:UserAddComponent},
    {path: 'users/edit/:username', component:UserEditComponent},
    {path: 'users/view/:username', component:UserViewComponent},
    {path: 'devices', component: DeviceListComponent},
    {path: 'devices/add', component:DeviceAddComponent},
    {path: 'devices/edit/:id', component:DeviceEditComponent},
    {path: 'devices/request', component: DeviceRequestListComponent},
    {path: 'devices/transfer', component: TransferListComponent},
    {path: 'devices/model', component: DeviceModelListComponent},
    {path: 'devices/model/add', component: DeviceModelAddComponent},
    {path: 'devices/model/edit/:id', component: DeviceModelEditComponent},
    {path: 'devices/parameter/add', component: DeviceParameterAddComponent},
    {path: 'devices/parameter/edit/:id', component: DeviceParameterEditComponent},
    {path: 'warehouses', component: WarehouseListComponent},
    {path: 'warehouses/add', component: WarehouseAddComponent},
    {path: 'warehouses/edit/:id', component: WarehouseEditComponent},
    {path: 'requests', component: RequestListComponent},
    {path: 'reports', component: ReportListComponent},


];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule {
}
