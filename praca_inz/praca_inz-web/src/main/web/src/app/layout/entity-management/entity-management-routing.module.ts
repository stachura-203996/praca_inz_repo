import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {DepartmentViewComponent} from "./department-view/department-view.component";
import {CompanyViewComponent} from "./company-view/company-view.component";
import {OfficeViewComponent} from "./office-view/office-view.component";



const routes: Routes = [
    {path: 'companies/view/:id', component:CompanyViewComponent},
    {path: 'offices/view/:id', component:OfficeViewComponent},
    {path: 'departments/view/:id', component: DepartmentViewComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EntityManagementRoutingModule {
}
