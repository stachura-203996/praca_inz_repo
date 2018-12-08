import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './shared';
import {LoginService} from "./login/login.service";
import {CookieService} from "ngx-cookie-service";

const routes: Routes = [
    { path: '', redirectTo: 'page', pathMatch: 'prefix' },
    { path: 'page', loadChildren: './layout/layout.module#LayoutModule', canActivate: [AuthGuard]},
    { path: 'login', loadChildren: './login/login.module#LoginModule' },
    { path: 'signup', loadChildren: './signup/signup.module#SignupModule' },
    { path: 'error', loadChildren: './server-error/server-error.module#ServerErrorModule' },
    { path: 'access-denied', loadChildren: './access-denied/access-denied.module#AccessDeniedModule' },
    { path: 'not-found', loadChildren: './not-found/not-found.module#NotFoundModule' },
    { path: '**', redirectTo: 'not-found' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes )],
    exports: [RouterModule],
    providers:[LoginService,CookieService]
})
export class AppRoutingModule {}
