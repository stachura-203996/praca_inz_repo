import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {SessionContextService} from '../services/session-context.service';
import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from '../services/message.service';
import {I18nService} from '../services/i18n/i18n.service';
import {UserService} from "../../layout/admin/components/administration/user-management/user.service";
import {LoggedUser} from "../../models/logged-user";

@Injectable()
export class GeneralRouteGuard implements CanActivate {

    user:LoggedUser;

  constructor(private userService:UserService,
              private messageService: MessageService,
              private i18nService: I18nService,
              private router: Router) {
      this.getLoggedUser();
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let available = false;


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

  getLoggedUser(){
      this.userService.getLoggedUser().subscribe(x=>this.user=x);
  }

}
