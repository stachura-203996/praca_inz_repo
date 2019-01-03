import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './shared';

const routes: Routes = [
    { path: '', redirectTo: 'page', pathMatch: 'prefix' },
    { path: 'page', loadChildren: './layout/layout.module#LayoutModule', canActivate: [AuthGuard]},
    { path: 'login', loadChildren: './login/login.module#LoginModule' },
    { path: 'signup', loadChildren: './signup/signup.module#SignupModule' },
    { path: 'error', loadChildren: './errors-pages/error.module#ErrorModule' },
    { path: '**', redirectTo: 'error/error-404' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes )],
    exports: [RouterModule],
})
export class AppRoutingModule {}
