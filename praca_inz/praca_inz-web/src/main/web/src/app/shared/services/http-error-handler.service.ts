import {Injectable} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {Router} from '@angular/router';
import {MessageService} from './message.service';
import {I18nService} from './i18n/i18n.service';
import {SessionContextService} from './session-context.service';
import { Observable, throwError } from 'rxjs';

@Injectable()
export class HttpErrorHandler {

  private badRequest = 400;
  private notAuthorizedUserErrorCode = 401;
  private forbiddenErrorCode = 403;
  private internalErrorCode = 500;

  constructor(private router: Router,
              private messageService: MessageService,
              private sessionContextService: SessionContextService,
              private i18nService: I18nService) {
  }

  handleError(error: HttpErrorResponse): Observable<Error> {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);

    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      if (error.status === this.notAuthorizedUserErrorCode || error.status === this.internalErrorCode) {
        this.messageService.error(this.i18nService.getMessage('session.expired.error'));
        this.sessionContextService.resetSession();
        this.router.navigateByUrl('/login');
      } else if (error.status === this.forbiddenErrorCode) {
        this.messageService.error(this.i18nService.getMessage('login.error.msg'));
      } else if (error.status === this.badRequest) {
        this.messageService.error(this.i18nService.getMessage('bad.request.error'));
      } else {
        console.error(
          `Backend returned code ${error.status}, ` +
          `body was: ${error.error}`);
      }

    }
    // return an ErrorObservable with a user-facing error message
    return throwError(error.error);
  }
}
