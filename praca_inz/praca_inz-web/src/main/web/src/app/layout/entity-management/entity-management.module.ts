import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CompanyViewComponent} from './company-view/company-view.component';
import {DepartmentViewComponent} from './department-view/department-view.component';
import {OfficeViewComponent} from './office-view/office-view.component';
import {EntityManagementRoutingModule} from "./entity-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
    imports: [
        CommonModule,
        EntityManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [CompanyViewComponent, DepartmentViewComponent, OfficeViewComponent]
})
export class EntityManagementModule {
}
