import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import {RoleGuardService} from "../shared/guard/role-guard.service";

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'main-page', pathMatch: 'prefix'},
            { path: 'main-page', loadChildren: './main-page/main-page.module#MainPageModule'},
            {path:  'profile', loadChildren: './profile/profile.module#ProfileModule',canActivate: [RoleGuardService],data:{allowedRoles: ['USER']}},
            {path: 'devices', loadChildren:'./device-management/device-management.module#DeviceManagementModule',canActivate: [RoleGuardService],data:{allowedRoles: ['USER']}},
            {path: 'warehouses', loadChildren: './warehouse-management/warehouse-management.module#WarehouseManagementModule',canActivate: [RoleGuardService],data:{allowedRoles: ['WAREHOUSEMAN','ADMIN']}},
            {path: 'employees', loadChildren: './employee-management/employee-management.module#EmployeeManagementModule',canActivate: [RoleGuardService],data:{allowedRoles: ['USER','MANAGER']}},
            {path:'admin', loadChildren: './admin/admin.module#AdminModule',canActivate: [RoleGuardService],data:{allowedRoles: ['ADMIN','COMPANY_ADMMIN']}},
            {path: 'notifications', loadChildren:'./notification/notifications.module#NotificationsModule',canActivate: [RoleGuardService],data:{allowedRoles: ['USER']}},
            {path: 'structures', loadChildren:'./entity-management/entity-management.module#EntityManagementModule',canActivate: [RoleGuardService],data:{allowedRoles: ['USER']}},
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
