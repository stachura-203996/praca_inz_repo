import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {retry, catchError} from 'rxjs/operators';
import {Router} from "@angular/router";
import {LoginService} from "../../login/login.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageService} from "../services/message.service";
import {Configuration} from "../../app.constants";

export class HttpErrorInterceptor implements HttpInterceptor {


    private badRequest = 400;
    private notAuthorizedUserErrorCode = 401;
    private forbiddenErrorCode = 403;
    private internalErrorCode = 500;

    //
    constructor(private router: Router,
                private  loginService: LoginService,
                private translate: TranslateService,
                private configuration: Configuration,
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
                            this.translate.get('session.expired.error').subscribe(x => {
                                this.messageService.error(x);
                            });
                            this.loginService.logout();
                        } else if (error.status === this.forbiddenErrorCode) {
                            this.translate.get('login.error.msg').subscribe(x => {
                                this.messageService.error(x);
                            });
                        } else if (error.status === this.badRequest) {

                            console.log(error.error.payload.message);
                            this.handle(error.error.payload.message);
                            this.translate.get("bad.request.error").subscribe(x => {
                                this.messageService.error(x);
                            });
                        } else {
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

    handle(message: string) {
        if (message == this.configuration.ERROR_COMPANY_NAME_TAKEN) {
            this.translate.get('comapny.name.taken.error').subscribe(translated => {
                this.messageService.error(translated)
            })

        } else if (message === this.configuration.OPTIMISTIC_LOCK) {
            this.translate.get('optimistic.lock').subscribe(x => {
                this.messageService.error(x);
            })

        } else if (message === this.configuration.ERROR_NO_OBJECT_IN_DATABASE) {
            this.translate.get('no.object.in.database.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_USERNAME_TAKEN) {
            this.translate.get('username.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_EMAIL_TAKEN) {
            this.translate.get('email.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_SAME_PASSWORD) {
            this.translate.get('same.password.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_DEPARTMENT_NAME_TAKEN) {
            this.translate.get('department.name.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_SERIAL_NUMBER_NAME_TAKEN) {
            this.translate.get('device.serial.number.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_DEVICE_MODEL_NAME_NAME_TAKEN) {
            this.translate.get('device.model.name.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_OFFICE_NAME_TAKEN) {
            this.translate.get('office.name.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_WAREHOUSE_NAME_TAKEN) {
            this.translate.get('warehouse.name.taken.error').subscribe(x => {
                this.messageService.error(x);
            })
        } else if (message === this.configuration.ERROR_NO_USERNAME_IN_DATABASE) {
            this.translate.get('no.username.in.database.error').subscribe(x => {
                this.messageService.error(x);
            })
        }else if (message === this.configuration.ERROR_INCORRECT_PASSWORD_RESET_DATA) {
            this.translate.get('incorrect.password.reset.data').subscribe(x => {
                this.messageService.error(x);
            })
        }else{
            this.translate.get('unknown.error').subscribe(x => {
                this.messageService.error(x);
            })
        }
    }
}
