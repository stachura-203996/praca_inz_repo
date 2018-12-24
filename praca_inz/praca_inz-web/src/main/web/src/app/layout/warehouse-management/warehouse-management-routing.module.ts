import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WarehouseUserComponent} from "./warehouse-user/warehouse-user.component";
import {WarehouseViewComponent} from "./warehouse-view/warehouse-view.component";
import {DeliveryRequestListComponent} from "./delivery-request-list/delivery-request-list.component";
import {DeliveryRequestAddComponent} from "./delivery-request-add/delivery-request-add.component";
import {DeliveryRequestViewComponent} from "./delivery-request-view/delivery-request-view.component";


const routes: Routes = [
    {path: '', component: WarehouseUserComponent },
    {path: 'view', component: WarehouseViewComponent },
    {path: 'delivery/requests', component: DeliveryRequestListComponent },
    {path:'delivery/request/add', component: DeliveryRequestAddComponent},
    {path:'delivery/request/view', component: DeliveryRequestViewComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class WarehouseManagementRoutingModule {}
