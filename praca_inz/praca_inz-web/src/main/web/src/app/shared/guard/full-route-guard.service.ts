import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';

import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from "../services/message.service";
import {LoginService} from "../../login/login.service";
import {UserService} from "../../layout/admin/components/administration/user-management/user.service";
import {TranslateService} from "@ngx-translate/core";

@Injectable()
export class FullRouteGuard implements CanActivate {



    constructor(private loginService: LoginService,
                private userService: UserService,
                private translate:TranslateService,
                private messageService: MessageService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let available = false;
        const user=this.loginService.user;

        if (user) {
            for (const role of user.roles) {

                if (UrlAvailabilityForUserRoles[role].includes(state.url)) {
                    available = true;
                    break;
                }
            }

            if (!available) {
                this.router.navigateByUrl('/ui/error/error404');
            }

            return available;
        } else {

            this.translate.get('session.expired.error').subscribe(x=>{
                this.messageService.error(x);
            });
            this.loginService.logout();
            return false;
        }
    }
}
