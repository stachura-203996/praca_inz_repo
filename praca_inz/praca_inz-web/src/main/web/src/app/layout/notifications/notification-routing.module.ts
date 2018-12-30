import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotificationUserComponent } from "./notification-user/notification-user.component";

const routes: Routes = [
    {path:'', component: NotificationUserComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class NotificationRoutingModule {}
