import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SubordinatesListComponent} from './subordinates-list/subordinates-list.component';
import {SubordinatesRequestsComponent} from './subordinates-requests/subordinates-requests.component';
import {EmployeeManagementRoutingModule} from "./employee-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";

@NgModule({
    imports: [
        CommonModule,
        EmployeeManagementRoutingModule,
        TranslateModule,
        FormsModule
    ],
    declarations: [SubordinatesListComponent, SubordinatesRequestsComponent]
})
export class EmployeeManagementModule {
}
