import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {LoginService} from "../../login/login.service";
import {decode} from "punycode";

@Injectable({
    providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

    constructor(public loginService: LoginService, public router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot): boolean {

        const user = this.loginService.getUser();
        const expectedRoles: string[] = route.data.allowedRoles;


        if (expectedRoles.some(x => user.roles.includes(x))) {
            return true;
        } else if (!this.loginService.checkCredentials()) {
            this.loginService.logout();
            return false;
        } else{
            this.router.navigateByUrl('/main-page')
        }
        return true;
    }
}
