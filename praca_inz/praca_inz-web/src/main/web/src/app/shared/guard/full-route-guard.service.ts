import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';

import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from "../services/message.service";
import {LoginService} from "../../login/login.service";
import {TranslateService} from "@ngx-translate/core";

@Injectable()
export class FullRouteGuard implements CanActivate {



    constructor(private loginService: LoginService,
                private messageService: MessageService,
                private translate:TranslateService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let available = false;
        const user = this.loginService.getUser();

        if (user) {
            for (const role of user.roles) {

                if (UrlAvailabilityForUserRoles[role].includes(state.url)) {
                    available = true;
                    break;
                }
            }

            if (!available) {
                this.router.navigateByUrl('/error404');
            }

            return available;
        } else {
            this.translate.get('session.expired.error').subscribe(x=>{
                this.messageService.error(x);
            })
            this.loginService.logout()
            return false;
        }
    }
}
