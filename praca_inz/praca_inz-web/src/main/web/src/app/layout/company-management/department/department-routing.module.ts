import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DepartmentListComponent} from "./department-list/department-list.component";
import {DepartmentEditComponent} from "./department-edit/department-edit.component";
import {DepartmentAddComponent} from "./department-add/department-add.component";



const routes: Routes = [
    {path: '', component: DepartmentListComponent },
    {path: 'edit', component: DepartmentEditComponent},
    {path:'add', component: DepartmentAddComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DepartmentRoutingModule {}
