import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {Router} from "@angular/router";
import {LoginService} from "../../login/login.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../services/message.service";

export class HttpErrorInterceptor implements HttpInterceptor {


    private badRequest = 400;
    private notAuthorizedUserErrorCode = 401;
    private forbiddenErrorCode = 403;
    private internalErrorCode = 500;
    private okCode=200;
    //
    constructor(private router: Router,
               private  loginService:LoginService,
                private translate: TranslateService,
                private messageService: MessageService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request)
            .pipe(
                retry(1),
                catchError((error: HttpErrorResponse) => {
                    let errorMessage = '';
                    if (error.error instanceof ErrorEvent) {
                        // client-side error
                        console.error('An error occurred:', error.error.message);
                    } else {
                        // The backend returned an unsuccessful response code.
                        // The response body may contain clues as to what went wrong,
                        if (error.status === this.notAuthorizedUserErrorCode || error.status === this.internalErrorCode) {
                            this.translate.get('session.expired.error').subscribe(x=>{
                                this.messageService.error(x);
                            });
                           this.loginService.logout();
                        } else if (error.status === this.forbiddenErrorCode) {
                            this.translate.get('login.error.msg').subscribe( x => {
                                this.messageService.error(x);
                            });
                        } else if (error.status === this.badRequest) {
                            this.translate.get("bad.request.error").subscribe( x => {
                                this.messageService.error(x);
                            });
                        } else if(error.status!=this.okCode) {
                            console.error(
                                `Backend returned code ${error.status}, ` +
                                `body was: ${error.error}`);
                        }

                    }
                    // window.alert(errorMessage);
                    return throwError(errorMessage);
                })
            )
    }
}
