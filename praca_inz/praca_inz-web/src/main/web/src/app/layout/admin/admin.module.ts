import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminRoutingModule} from "./admin-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {CompanyListComponent} from "./components/entity-management/company/company-list/company-list.component";
import {CompanyAddComponent} from "./components/entity-management/company/company-add/company-add.component";
import {CompanyEditComponent} from "./components/entity-management/company/company-edit/company-edit.component";
import {DepartmentListComponent} from "./components/entity-management/department/department-list/department-list.component";
import {DepartmentAddComponent} from "./components/entity-management/department/department-add/department-add.component";
import {DepartmentEditComponent} from "./components/entity-management/department/department-edit/department-edit.component";
import {OfficeListComponent} from "./components/entity-management/office/office-list/office-list.component";
import {OfficeAddComponent} from "./components/entity-management/office/office-add/office-add.component";
import {OfficeEditComponent} from "./components/entity-management/office/office-edit/office-edit.component";
import { DashboardComponent } from './components/administration/dashboard/dashboard.component';
import {ChatComponent, NotificationComponent, TimelineComponent} from "./components/administration/components";

@NgModule({
    imports: [
        CommonModule,
        AdminRoutingModule,
        TranslateModule,
        FormsModule
    ],
    declarations: [
        CompanyListComponent,
        CompanyAddComponent,
        CompanyEditComponent,
        DepartmentListComponent,
        DepartmentAddComponent,
        DepartmentEditComponent,
        OfficeListComponent,
        OfficeAddComponent,
        OfficeEditComponent,
        DashboardComponent,
        ChatComponent,
        NotificationComponent,
        TimelineComponent
    ]
})
export class AdminModule {
}
