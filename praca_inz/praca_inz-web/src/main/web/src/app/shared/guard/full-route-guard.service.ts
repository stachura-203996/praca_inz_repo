import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {SessionContextService} from '../services/session-context.service';
import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {MessageService} from '../services/message.service';
import {I18nService} from '../services/i18n/i18n.service';

@Injectable()
export class FullRouteGuard implements CanActivate {

  constructor(private sessionContextService: SessionContextService,
              private messageService: MessageService,
              private i18nService: I18nService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let available = false;
    const user = this.sessionContextService.getUser();

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
      this.messageService.error(this.i18nService.getMessage('session.expired.error'));
      this.sessionContextService.resetSession();
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
