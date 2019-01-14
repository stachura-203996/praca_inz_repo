import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from '../services/message.service';
import {UserService} from "../../layout/admin/components/administration/user-management/user.service";
import {LoginService} from "../../login/login.service";
import {LoggedUser} from "../../models/logged-user";

@Injectable()
export class GeneralRouteGuard implements CanActivate {

    user:LoggedUser;

    constructor(
                private loginService:LoginService,
                private messageService: MessageService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let available = false;
       this.user=this.loginService.user;

        if (this.user) {
            for (const role of this.user.roles) {
                if (UrlAvailabilityForUserRoles[role].includes(
                    state.url.substring(0, state.url.indexOf('/', 1) > 1 ? state.url.indexOf('/', 1) : state.url.length))) {
                    available = true;
                    break;
                }
            }

            if (!available) {
                this.router.navigateByUrl('/error/error404');
            }

            return available;
        } else {
            this.router.navigateByUrl('/');
            return false;
        }
    }


}
