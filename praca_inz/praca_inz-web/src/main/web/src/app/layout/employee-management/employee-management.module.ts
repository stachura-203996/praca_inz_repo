import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EmployeeManagementRoutingModule} from "./employee-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {ReportUserComponent} from "./report-user/report-user.component";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { SystemMessageAddComponent } from './system-message-add/system-message-add.component';
import { SystemMessageListComponent } from './system-message-list/system-message-list.component';
import { SystemMessageEditComponent } from './system-message-edit/system-message-edit.component';
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {SubordinatesRequestsComponent} from "./subordinates-requests/subordinates-requests.component";
import { ReportViewComponent } from './report-view/report-view.component';
import { ReportAddComponent } from './report-add/report-add.component';


@NgModule({
    imports: [
        CommonModule,
        EmployeeManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [EmployeesListComponent, SubordinatesRequestsComponent, ReportUserComponent, SystemMessageAddComponent, SystemMessageListComponent, SystemMessageEditComponent, ReportViewComponent, ReportAddComponent]
})
export class EmployeeManagementModule {
}
