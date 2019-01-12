import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';

import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from "../services/message.service";
import {I18nService} from "../services/i18n/i18n.service";
import {LoginService} from "../../login/login.service";
import {UserService} from "../../layout/admin/components/administration/user-management/user.service";
import {LoggedUser} from "../../models/logged-user";


@Injectable()
export class FullRouteGuard implements CanActivate {

    user: LoggedUser;

    constructor(private loginService: LoginService,
                private userService: UserService,
                private messageService: MessageService,
                private i18nService: I18nService,
                private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        let available = false;
        this.userService.getLoggedUser().subscribe(x=>this.user=x);

        if (this.user) {
            for (const role of this.user.roles) {

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
            this.messageService.error(this.i18nService.getMessage('session.expired.error'));
            this.loginService.logout();
            return false;
        }
    }
}
