import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CompanyListComponent} from "./components/entity-management/company/company-list/company-list.component";
import {CompanyAddComponent} from "./components/entity-management/company/company-add/company-add.component";
import {CompanyEditComponent} from "./components/entity-management/company/company-edit/company-edit.component";
import {OfficeListComponent} from "./components/entity-management/office/office-list/office-list.component";
import {OfficeAddComponent} from "./components/entity-management/office/office-add/office-add.component";
import {OfficeEditComponent} from "./components/entity-management/office/office-edit/office-edit.component";
import {DashboardComponent} from "./components/administration/dashboard/dashboard.component";


const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'prefix' },
    {path: 'companies', component: CompanyListComponent},
    {path: 'companies/add', component:CompanyAddComponent},
    {path: 'companies/edit', component:CompanyEditComponent},
    {path: 'offices', component: OfficeListComponent},
    {path: 'offices/add', component:OfficeAddComponent},
    {path: 'offices/edit', component:OfficeEditComponent},
    {path: 'departments', component: OfficeListComponent},
    {path: 'departments/add', component:OfficeAddComponent},
    {path: 'departments/edit', component:OfficeEditComponent},
    {path: 'dashboard', component: DashboardComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule {
}
