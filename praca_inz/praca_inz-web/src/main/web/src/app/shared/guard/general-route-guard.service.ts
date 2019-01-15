import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from "../services/message.service";
import {LoginService} from "../../login/login.service";
import {TranslateService} from "@ngx-translate/core";


@Injectable()
export class GeneralRouteGuard implements CanActivate {

    constructor(
                private messageService: MessageService,
                private loginService:LoginService,
                private translateService:TranslateService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let available = false;
        const user = this.loginService.getUser();

        if (user) {
            for (const role of user.roles) {
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
            this.translateService.get('session.expired.error').subscribe(x=>{
                this.messageService.error(x);
            })
            this.loginService.logout();
            return false;
        }
    }
}
