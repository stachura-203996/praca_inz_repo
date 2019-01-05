import {CommonModule} from '@angular/common';
import {HttpClient, HttpClientModule} from '@angular/common/http';
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
import {I18nService} from "./shared/services/i18n/i18n.service";
import {ToastrModule} from "ngx-toastr";
import {SessionContextService} from "./shared/services/session-context.service";
import {UserService} from "./layout/admin/components/administration/user-management/user.service";
import {DeviceService} from "./layout/device-management/device.service";
import {NotificationService} from "./layout/notification/notification.service";
import {PasswordMatchDirective} from "./directives/password-match.directive";
import {WarehouseService} from "./layout/warehouse-management/warehouse.service";
import {SystemMessageService} from "./layout/main-page/system-message.service";
import {ReportService} from "./layout/employee-management/report.service";
import {EmailMatchDirective} from "./directives/email-match.directive";
import {NotNaughtDirective} from "./directives/not-naught.directive";
import {NotNegativeDirective} from "./directives/not-negative.directive";
import {NotRealNumberDirective} from "./directives/not-real-number.directive";


export const createTranslateLoader = (http: HttpClient) => {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
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
    ],
    declarations: [
        AppComponent,
        ConfirmDialogComponent,
        PasswordMatchDirective,
        EmailMatchDirective,
        NotNaughtDirective,
        NotNegativeDirective,
        NotRealNumberDirective
    ],
    providers: [
        AuthGuard,
        LoginService,
        CookieService,
        UserService,
        HttpService,
        Configuration,
        ProfileService,
        MessageService,
        SystemMessageService,
        DeviceService,
        I18nService,
        ReportService,
        SessionContextService,
        NotificationService,
        WarehouseService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
