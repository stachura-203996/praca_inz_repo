import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'main-page', pathMatch: 'prefix' },
            { path: 'main-page', loadChildren: './main-page/main-page.module#MainPageModule' },
            {path:  'profile', loadChildren: './profile/profile.module#ProfileModule'},
            {path: 'settings', loadChildren:'./settings/settings.module#SettingsModule'},
            {path: 'devices', loadChildren:'./device-management/device-management.module#DeviceManagementModule'},
            {path: 'warehouses', loadChildren: './warehouse-management/warehouse-management.module#WarehouseManagementModule'},
            {path: 'subordinates', loadChildren: './employee-management/employee-management.module#EmployeeManagementModule'},
            {path:'admin', loadChildren: './admin/admin.module#AdminModule'},
            {path: 'notifications', loadChildren:'./notification/notifications.module#NotificationsModule'}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
