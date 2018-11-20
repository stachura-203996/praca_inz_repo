import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyListComponent } from './components/company/company-list/company-list.component';
import { CompanyAddComponent } from './components/company/company-add/company-add.component';
import { ComapnyEditComponent } from './components/company/comapny-edit/comapny-edit.component';
import { DepartmentListComponent } from './components/department/department-list/department-list.component';
import { DepartmentAddComponent } from './components/department/department-add/department-add.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [CompanyListComponent, CompanyAddComponent, ComapnyEditComponent, DepartmentListComponent, DepartmentAddComponent]
})
export class CompanyManagementModule { }
