import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CompanyListComponent} from "./company-list/company-list.component";
import {CompanyEditComponent} from "./company-edit/company-edit.component";
import {CompanyAddComponent} from "./company-add/company-add.component";


const routes: Routes = [
    {path: '', component: CompanyListComponent },
    {path: 'edit', component: CompanyEditComponent},
    {path:'add', component: CompanyAddComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CompanyRoutingModule {}
