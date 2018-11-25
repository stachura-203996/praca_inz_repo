import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SubordinatesListComponent} from "./subordinates-list/subordinates-list.component";
import {SubordinatesRequestsComponent} from "./subordinates-requests/subordinates-requests.component";


const routes: Routes = [
    {path: '', component: SubordinatesListComponent },
    {path: 'requests', component: SubordinatesRequestsComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EmployeeManagementRoutingModule {}
