import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OfficeListComponent} from "./office-list/office-list.component";
import {OfficeEditComponent} from "./office-edit/office-edit.component";
import {OfficeAddComponent} from "./office-add/office-add.component";

const routes: Routes = [
    {path: '', component: OfficeListComponent },
    {path: 'edit', component: OfficeEditComponent},
    {path:'add', component: OfficeAddComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class OfficeRoutingModule {}
