import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpResponse,
    HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {Router} from "@angular/router";
import {MessageService} from "../services/message.service";
import {SessionContextService} from "../services/session-context.service";
import {I18nService} from "../services/i18n/i18n.service";

export class HttpErrorInterceptor implements HttpInterceptor {


    private badRequest = 400;
    private notAuthorizedUserErrorCode = 401;
    private forbiddenErrorCode = 403;
    private internalErrorCode = 500;
    //
    // constructor(private router: Router,
    //             private messageService: MessageService,
    //             private sessionContextService: SessionContextService,
    //             private i18nService: I18nService) {
    // }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request)
            .pipe(
                retry(1),
                catchError((error: HttpErrorResponse) => {
                    let errorMessage = '';
                    if (error.error instanceof ErrorEvent) {
                        // client-side error
                        errorMessage = `Error: ${error.error.message}`;
                    } else {
                        // The backend returned an unsuccessful response code.
                        // The response body may contain clues as to what went wrong,
                        if (error.status === this.notAuthorizedUserErrorCode || error.status === this.internalErrorCode) {
                            // this.messageService.error(this.i18nService.getMessage('session.expired.error'));
                            // this.router.navigateByUrl('/login');
                        } else if (error.status === this.forbiddenErrorCode) {
                            // this.messageService.error(this.i18nService.getMessage('login.error.msg'));
                        } else if (error.status === this.badRequest) {
                            // this.messageService.error(this.i18nService.getMessage('bad.request.error'));
                        } else {
                            // console.error(
                            //     `Backend returned code ${error.status}, ` +
                            //     `body was: ${error.error}`);
                        }

                    }
                    window.alert(errorMessage);
                    return throwError(errorMessage);
                })
            )
    }
}
