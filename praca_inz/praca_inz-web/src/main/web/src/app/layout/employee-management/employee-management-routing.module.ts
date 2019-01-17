import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {ReportUserComponent} from "./reports/report-user/report-user.component";
import {ReportViewComponent} from "./reports/report-view/report-view.component";
import {ReportAddComponent} from "./reports/report-add/report-add.component";
import {RequestUserComponent} from "./request-user/request-user.component";
import {ReportRequestAddComponent} from "./reports/report-request-add/report-request-add.component";
import {UserViewComponent} from "./user-view/user-view.component";
import {GeneralRouteGuard} from "../../shared/guard/general-route-guard.service";
import {FullRouteGuard} from "../../shared/guard/full-route-guard.service";

const routes: Routes = [
    {path: 'subordinates', component: EmployeesListComponent/*,canActivate: [FullRouteGuard] */},
    {path: 'reports', component: ReportUserComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'reports/view/:id', component: ReportViewComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'reports/add', component: ReportAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'reports/request/add/:id', component: ReportRequestAddComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'requests', component: RequestUserComponent/*,canActivate: [GeneralRouteGuard]*/},
    {path: 'user/view/:username', component: UserViewComponent/*,canActivate: [GeneralRouteGuard]*/},

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EmployeeManagementRoutingModule {}
