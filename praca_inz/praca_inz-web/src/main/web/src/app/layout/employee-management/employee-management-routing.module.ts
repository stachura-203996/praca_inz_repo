import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SubordinatesRequestsComponent} from "./subordinates-requests/subordinates-requests.component";
import {ReportUserComponent} from "./report-user/report-user.component";
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {ReportViewComponent} from "./report-view/report-view.component";
import {ReportAddComponent} from "./report-add/report-add.component";


const routes: Routes = [
    {path: 'subordinates', component: EmployeesListComponent },
    {path: 'subordinates/requests', component: SubordinatesRequestsComponent},
    {path: 'reports', component: ReportUserComponent},
    {path: 'reports/view/:id', component: ReportViewComponent},
    {path: 'reports/add', component: ReportAddComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EmployeeManagementRoutingModule {}
