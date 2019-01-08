import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ServerErrorComponent} from "./500/server-error.component";
import {NotFoundComponent} from "./404/not-found.component";
import {AccessDeniedComponent} from "./403/access-denied.component";
import {RequiresAuthenticationComponent} from "./401/requires-authentication.component";
import {BadRequestComponent} from "./400/bad-request.component";

const routes: Routes = [
    {path: 'error-500', component: ServerErrorComponent},
    {path: 'error-404', component: NotFoundComponent},
    {path: 'error-403', component: AccessDeniedComponent},
    {path: 'error-401', component: RequiresAuthenticationComponent},
    {path: 'error-400', component: BadRequestComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ErrorRoutingModule {
}
