import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import {GeneralRouteGuard} from "../shared/guard/general-route-guard.service";
import {AuthGuard} from "../shared/guard";
import {FullRouteGuard} from "../shared/guard/full-route-guard.service";

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'main-page', pathMatch: 'prefix'},
            { path: 'main-page', loadChildren: './main-page/main-page.module#MainPageModule',canActivate: [AuthGuard]},
            {path:  'profile', loadChildren: './profile/profile.module#ProfileModule',canActivate: [GeneralRouteGuard]},
            {path: 'devices', loadChildren:'./device-management/device-management.module#DeviceManagementModule',canActivate: [GeneralRouteGuard]},
            {path: 'warehouses', loadChildren: './warehouse-management/warehouse-management.module#WarehouseManagementModule',canActivate: [GeneralRouteGuard]},
            {path: 'employees', loadChildren: './employee-management/employee-management.module#EmployeeManagementModule',canActivate: [GeneralRouteGuard]},
            {path:'admin', loadChildren: './admin/admin.module#AdminModule',canActivate: [GeneralRouteGuard]},
            {path: 'notifications', loadChildren:'./notification/notifications.module#NotificationsModule',canActivate: [GeneralRouteGuard]},
            {path: 'structures', loadChildren:'./entity-management/entity-management.module#EntityManagementModule',canActivate: [GeneralRouteGuard]},
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
