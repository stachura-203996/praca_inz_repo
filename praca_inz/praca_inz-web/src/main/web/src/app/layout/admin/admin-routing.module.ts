import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CompanyListComponent} from "./components/entity-management/company/company-list/company-list.component";
import {CompanyAddComponent} from "./components/entity-management/company/company-add/company-add.component";
import {CompanyEditComponent} from "./components/entity-management/company/company-edit/company-edit.component";
import {OfficeListComponent} from "./components/entity-management/office/office-list/office-list.component";
import {OfficeAddComponent} from "./components/entity-management/office/office-add/office-add.component";
import {OfficeEditComponent} from "./components/entity-management/office/office-edit/office-edit.component";
import {DashboardComponent} from "./components/administration/dashboard/dashboard.component";
import {DepartmentListComponent} from "./components/entity-management/department/department-list/department-list.component";
import {DepartmentAddComponent} from "./components/entity-management/department/department-add/department-add.component";
import {DepartmentEditComponent} from "./components/entity-management/department/department-edit/department-edit.component";
import {UserListComponent} from "./components/administration/user-management/components/user-list/user-list.component";
import {UserAddComponent} from "./components/administration/user-management/components/user-add/user-add.component";
import {UserEditComponent} from "./components/administration/user-management/components/user-edit/user-edit.component";
import {UserViewComponent} from "./components/administration/user-management/components/user-view/user-view.component";
import {DeviceViewComponent} from "../device-management/device-view/device-view.component";
import {DeviceListComponent} from "./components/entity-management/device/device-list/device-list.component";
import {DeviceAddComponent} from "./components/entity-management/device/device-add/device-add.component";
import {DeviceEditComponent} from "./components/entity-management/device/device-edit/device-edit.component";
import {DeviceRequestListComponent} from "./components/entity-management/device/device-request-list/device-request-list.component";
import {TransferListComponent} from "./components/entity-management/device/transfer-list/transfer-list.component";
import {DeviceParameterAddComponent} from "./components/entity-management/device/device-parameter-add/device-parameter-add.component";
import {DeviceParameterEditComponent} from "./components/entity-management/device/device-parameter-edit/device-parameter-edit.component";
import {DeviceTypeListComponent} from "./components/entity-management/device/device-type-list/device-type-list.component";
import {DeviceTypeAddComponent} from "./components/entity-management/device/device-type-add/device-type-add.component";
import {DeviceTypeEditComponent} from "./components/entity-management/device/device-type-edit/device-type-edit.component";
import {DeviceTypeViewComponent} from "../device-management/device-type-view/device-type-view.component";



const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'prefix' },
    {path: 'dashboard', component: DashboardComponent},
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
    {path: 'devices/type', component: DeviceTypeListComponent},
    {path: 'devices/type/add', component: DeviceTypeAddComponent},
    {path: 'devices/type/edit/:id', component: DeviceTypeEditComponent},

    {path: 'devices/parameter/add', component: DeviceParameterAddComponent},
    {path: 'devices/parameter/edit/:id', component: DeviceParameterEditComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule {
}
