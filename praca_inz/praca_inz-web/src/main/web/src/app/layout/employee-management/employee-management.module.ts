import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EmployeeManagementRoutingModule} from "./employee-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import { RequestUserComponent } from './request-user/request-user.component';
import {UserViewComponent} from "./user-view/user-view.component";
import {ConfirmationViewComponent} from "./reports/confirmation-view/confirmation-view.component";
import {ConfirmationUserComponent} from "./reports/confirmation-user/confirmation-user.component";
import {ConfirmationAddComponent} from "./reports/confirmation-add/confirmation-add.component";
import {ConfirmationRequestAddComponent} from "./reports/confirmation-request-add/confirmation-request-add.component";


@NgModule({
    imports: [
        CommonModule,
        EmployeeManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [EmployeesListComponent,UserViewComponent, ConfirmationUserComponent, ConfirmationViewComponent, ConfirmationAddComponent, RequestUserComponent, ConfirmationRequestAddComponent]
})
export class EmployeeManagementModule {
}
