import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {RequestUserComponent} from "./request-user/request-user.component";
import {UserViewComponent} from "./user-view/user-view.component";
import {RoleGuardService} from "../../shared/guard/role-guard.service";
import {ConfirmationViewComponent} from "./reports/confirmation-view/confirmation-view.component";
import {ConfirmationUserComponent} from "./reports/confirmation-user/confirmation-user.component";
import {ConfirmationAddComponent} from "./reports/confirmation-add/confirmation-add.component";
import {ConfirmationRequestAddComponent} from "./reports/confirmation-request-add/confirmation-request-add.component";


const routes: Routes = [
    {path: 'subordinates', component: EmployeesListComponent,canActivate: [RoleGuardService],data:{allowedRoles: ['MANAGER']}},
    {path: 'confirmations', component: ConfirmationUserComponent},
    {path: 'confirmations/view/:id', component: ConfirmationViewComponent},
    {path: 'confirmations/add', component: ConfirmationAddComponent},
    {path: 'confirmations/request/add/:id', component: ConfirmationRequestAddComponent},
    {path: 'requests', component: RequestUserComponent},
    {path: 'user/view/:username', component: UserViewComponent},

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class EmployeeManagementRoutingModule {}
