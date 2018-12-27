import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SubordinatesListComponent} from "./subordinates-list/subordinates-list.component";
import {SubordinatesRequestsComponent} from "./subordinates-requests/subordinates-requests.component";
import {ReportUserComponent} from "./report-user/report-user.component";


const routes: Routes = [
    {path: 'subordinates', component: SubordinatesListComponent },
    {path: 'subordinates/requests', component: SubordinatesRequestsComponent},
    {path: 'reports', component: ReportUserComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EmployeeManagementRoutingModule {}
