import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {SessionContextService} from '../services/session-context.service';
import {MessageService} from '../services/message.service';
import {I18nService} from '../services/i18n/i18n.service';

@Injectable()
export class LoggedUserGuard implements CanActivate {

  constructor(private sessionContextService: SessionContextService,
              private messageService: MessageService,
              private i18nService: I18nService,
              private router: Router) {
  }

  canActivate(): boolean {
    if (this.sessionContextService.getUser() == null) {
      this.sessionContextService.resetSession();
      this.router.navigateByUrl('/login');
      return false;
    } else {
      return true;
    }
  }
}
