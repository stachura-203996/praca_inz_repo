import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthGuard} from './shared';
import {ConfirmDialogComponent} from "./confirm-dialog/confirm-dialog.component";
import {LoginService} from "./login/login.service";
import {CookieService} from "ngx-cookie-service";
import {HttpService} from "./shared/services/http.service";
import {Configuration} from "./app.constants";
import {ProfileService} from "./layout/profile/profile.service";
import {MessageService} from "./shared/services/message.service";
import {ToastContainerModule, ToastrModule} from "ngx-toastr";
import {SessionContextService} from "./shared/services/session-context.service";
import {UserService} from "./layout/admin/components/administration/user-management/user.service";
import {DeviceService} from "./layout/device-management/device.service";
import {NotificationService} from "./layout/notification/notification.service";
import {WarehouseService} from "./layout/warehouse-management/warehouse.service";
import {SystemMessageService} from "./layout/main-page/system-message.service";
import {ReportService} from "./layout/employee-management/report.service";
import {BsDropdownModule, BsModalService} from "ngx-bootstrap";
import {MatSelectModule} from "@angular/material";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpErrorInterceptor} from "./shared/interceptor/http-error.interceptor";
import {FullRouteGuard} from "./shared/guard/full-route-guard.service";
import {GeneralRouteGuard} from "./shared/guard/general-route-guard.service";


export const createTranslateLoader = (http: HttpClient) => {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        HttpClientModule,
        MatSelectModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        HttpClientModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: createTranslateLoader,
                deps: [HttpClient]
            }
        }),
        AppRoutingModule,
        ToastrModule.forRoot({
            positionClass: 'toast-top-center',
            maxOpened: 2,
            autoDismiss: true
        }),
        ToastContainerModule,
        BsDropdownModule.forRoot(),
    ],
    declarations: [
        AppComponent,
        ConfirmDialogComponent,
    ],
    providers: [
        AuthGuard,
        FullRouteGuard,
        GeneralRouteGuard,
        LoginService,
        CookieService,
        UserService,
        HttpService,
        Configuration,
        ProfileService,
        MessageService,
        SystemMessageService,
        DeviceService,
        BsModalService,
        ReportService,
        SessionContextService,
        NotificationService,
        WarehouseService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpErrorInterceptor,
            multi: true
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
