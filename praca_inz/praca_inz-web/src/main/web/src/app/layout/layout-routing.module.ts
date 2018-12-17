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
            { path: 'charts', loadChildren: './templates/charts/charts.module#ChartsModule' },
            { path: 'tables', loadChildren: './templates/tables/tables.module#TablesModule' },
            { path: 'forms', loadChildren: './templates/form/form.module#FormModule' },
            { path: 'bs-element', loadChildren: './templates/bs-element/bs-element.module#BsElementModule' },
            { path: 'grid', loadChildren: './templates/grid/grid.module#GridModule' },
            { path: 'components', loadChildren: './templates/bs-component/bs-component.module#BsComponentModule' },
            { path: 'blank-page', loadChildren: './templates/blank-page/blank-page.module#BlankPageModule' },
            {path:  'profile', loadChildren: './profile/profile.module#ProfileModule'},
            {path: 'users', loadChildren:'./user-management/user-management.module#UserManagementModule'},
            {path: 'mail', loadChildren:'./mail/mail.module#MailModule'},
            {path: 'settings', loadChildren:'./settings/settings.module#SettingsModule'},
            {path: 'devices', loadChildren:'./device-management/device-management.module#DeviceManagementModule'},
            {path: 'warehouse', loadChildren: './warehouse-management/warehouse-management.module#WarehouseManagementModule'},
            {path: 'subordinates', loadChildren: './employee-management/employee-management.module#EmployeeManagementModule'},
            {path:'admin', loadChildren: './admin/admin.module#AdminModule'}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
