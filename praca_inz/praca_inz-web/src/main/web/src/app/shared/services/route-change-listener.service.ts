import {Injectable} from '@angular/core';
import {NavigationStart, Router, RouterEvent} from '@angular/router';
import {SessionContextService} from './session-context.service';
import {Subject} from "rxjs";

@Injectable()
export class RouteChangeListenerService {

  public localeChanged = new Subject<any>();

  constructor(private router: Router,
              private sessionContextService: SessionContextService) {
    this.router.events.subscribe((event: RouterEvent) => {
      if (event instanceof NavigationStart) {
        this.checkLocale();
      }
    });
  }

  private checkLocale() {
    const locale = navigator.language;
    const sessionLocale = this.sessionContextService.getLocale();

    if (sessionLocale === undefined || sessionLocale !== locale) {
      this.sessionContextService.setLocale(locale);
      this.localeChanged.next();
    }
  }
}
