import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {ReportUserComponent} from "./reports/report-user/report-user.component";
import {ReportViewComponent} from "./reports/report-view/report-view.component";
import {ReportAddComponent} from "./reports/report-add/report-add.component";
import {RequestUserComponent} from "./request-user/request-user.component";
import {ReportRequestAddComponent} from "./reports/report-request-add/report-request-add.component";
import {UserViewComponent} from "./user-view/user-view.component";

const routes: Routes = [
    {path: 'subordinates', component: EmployeesListComponent },
    {path: 'reports', component: ReportUserComponent},
    {path: 'reports/view/:id', component: ReportViewComponent},
    {path: 'reports/add', component: ReportAddComponent},
    {path: 'reports/request/add/:id', component: ReportRequestAddComponent},
    {path: 'requests', component: RequestUserComponent},
    {path: 'user/view/:username', component: UserViewComponent},

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EmployeeManagementRoutingModule {}
