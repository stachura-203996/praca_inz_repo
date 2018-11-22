import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {SessionContextService} from '../services/session-context.service';

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private sessionContextService: SessionContextService,
              private router: Router) {
  }

  canActivate(): boolean {
    if (this.sessionContextService.getUser() == null) {
      return true;
    } else {
      this.router.navigateByUrl('/');
      return false;
    }
  }
}
