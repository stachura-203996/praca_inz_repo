import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotificationUserComponent } from "./notification-user/notification-user.component";
import {NotificationViewComponent} from "./notification-view/notification-view.component";

const routes: Routes = [
    {path: 'view', component: NotificationViewComponent },
    {path:'', component: NotificationUserComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class NotificationRoutingModule {}
